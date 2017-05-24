(ns facts.routes.ws
  (:require [compojure.core :refer [GET POST defroutes]]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [mount.core :refer [defstate]]
            [taoensso.sente :as sente]
            [taoensso.sente.server-adapters.immutant
             :refer [sente-web-server-adapter]])
  (:require [facts.db.core :as db]))



(let [connection (sente/make-channel-socket!
                   sente-web-server-adapter
                   {:user-id-fn (fn [ring-req]
                                  (get-in ring-req [:params :client-id]))})]
  (def ring-ajax-post (:ajax-post-fn connection))
  (def ring-ajax-get-or-ws-handshake (:ajax-get-or-ws-handshake-fn connection))
  (def ch-chsk (:ch-recv connection))
  (def chsk-send! (:send-fn connection))
  (def connected-uids (:connected-uids connection)))


;; An atom holding the set of channels.
;; (defonce channels (atom #{}))


;; (defn connect!
;;   "Add the channel to the set of channels."
;;   [channel]
;;   (log/info "channel open")
;;   (swap! channels conj channel))


;; (defn disconnect!
;;   "Remove the channel from the set of channels."
;;   [channel {:keys [code reason]}]
;;   (log/info "close code:" code "reason:" reason)
;;   (swap! channels clojure.set/difference #{channel}))


;; (defn encode-transit
;;   "Marshall the fact to a string."
;;   [fact]
;;   (let [out (java.io.ByteArrayOutputStream. 4096)
;;         writer (transit/writer out :json)]
;;     (transit/write writer fact)
;;     (.toString out)))


;; (defn decode-transit
;;   "Unmarshall the fact from a string."
;;   [fact]
;;   (let [in (java.io.ByteArrayInputStream. (.getBytes fact))
;;         reader (transit/reader in :json)]
;;     (transit/read reader)))

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

;; (defn handle-fact!
;;   "Save a new incmoming fact, and update all clients if successfull."
;;   [channel fact]
;;   (let [response (-> fact
;;                    decode-transit
;;                    (assoc :timestamp (java.util.Date.))
;;                    save-fact!)]
;;     (if (:errors response)
;;       ;; if errors in response send back on invoking client channel
;;       (do
;;       (println "trjl> response: " response)
;;       (async/send! channel
;;                   (encode-transit response))
;;       )
;;       ;; else send the newly added fact to all clients
;;       (do
;;       (println "trjl> response: " response)
;;       (println "trjl> fact    : " fact)
;;       (println "trjl> enc fact: " (encode-transit fact))
;;       (doseq
;;         [channel @channels]
;;         (async/send! channel (encode-transit response)))))))

(defn handle-fact!
  [{:keys [id client-id ?data]}]
  (when (= id :facts/add-fact)
    (let [response (-> ?data
                    (assoc :timestamp (java.util.Date.)) save-message!)]
      (if (:errors response)
        (chsk-send! client-id [:facts/error response])
        (doseq [uid (:any @connected-uids)]
          (chsk-send! uid [:facts/add-fact response]))))))


;; (defn ws-handler
;;   "Handle the incoming channel event."
;;   [request]
;;   (async/as-channel
;;     request
;;     {:on-open    connect!
;;      :on-close   disconnect!
;;      :on-message handle-fact!}))

(defn stop-router!
  [stop-fn]
  (when stop-fn (stop-fn)))


(defn start-router!
  []
  (sente/start-chsk-router! ch-chsk handle-fact!))


(defstate
  router
    :start (start-router!)
    :stop (stop-router! router))

;; (defroutes
;;   websocket-routes
;;   (GET "/ws" [] ws-handler))

(defroutes websocket-routes
 (GET "/ws" req (ring-ajax-get-or-ws-handshake req))
 (POST "/ws" req (ring-ajax-post req)))


