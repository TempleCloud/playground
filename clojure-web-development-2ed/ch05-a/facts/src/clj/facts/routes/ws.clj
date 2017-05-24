(ns facts.routes.ws
  (:require [compojure.core :refer [GET defroutes]]
            [clojure.tools.logging :as log]
            [immutant.web.async :as async]
            [cognitect.transit :as transit]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [facts.db.core :as db]))


;; An atom holding the set of channels.
(defonce channels (atom #{}))


(defn connect!
  "Add the channel to the set of channels."
  [channel]
  (log/info "channel open")
  (swap! channels conj channel))


(defn disconnect!
  "Remove the channel from the set of channels."
  [channel {:keys [code reason]}]
  (log/info "close code:" code "reason:" reason)
  (swap! channels clojure.set/difference #{channel}))


(defn encode-transit
  "Marshall the fact to a string."
  [fact]
  (let [out (java.io.ByteArrayOutputStream. 4096)
        writer (transit/writer out :json)]
    (transit/write writer fact)
    (.toString out)))


(defn decode-transit
  "Unmarshall the fact from a string."
  [fact]
  (let [in (java.io.ByteArrayInputStream. (.getBytes fact))
        reader (transit/reader in :json)]
    (transit/read reader)))

(defn validate-fact
  "Vallidate the fact"
  [params]
  (first
   (b/validate
    params
    :side_1  [v/required [v/min-count 1]]
    :side_2  [v/required [v/min-count 1]])))

(defn save-fact!
  "Save the fact"
  [fact]
  (if-let [errors (validate-fact fact)]
    {:errors errors}
    (do
      (db/save-fact!
        (-> fact
          (assoc :timestamp (java.util.Date.))
          (assoc :user_id 1)))
      fact)))


(defn handle-fact!
  "Save a new incmoming fact, and update all clients if successfull."
  [channel fact]
  (let [response (-> fact
                   decode-transit
                   (assoc :timestamp (java.util.Date.))
                   save-fact!)]
    (if (:errors response)
      ;; if errors in response send back on invoking client channel
      (do
      (println "trjl> response: " response)
      (async/send! channel
                  (encode-transit response))
      )
      ;; else send the newly added fact to all clients
      (do
      (println "trjl> response: " response)
      (println "trjl> fact    : " fact)
      (println "trjl> enc fact: " (encode-transit fact))
      (doseq
        [channel @channels]
        ;(async/send! channel (encode-transit fact)))
        (async/send! channel (encode-transit response)))
      )
      )))


(defn ws-handler
  "Handle the incoming channel event."
  [request]
  (async/as-channel
    request
    {:on-open    connect!
     :on-close   disconnect!
     :on-message handle-fact!}))

(defroutes
  websocket-routes
  (GET "/ws" [] ws-handler))
