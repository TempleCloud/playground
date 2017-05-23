(ns facts.ws
  (:require [cognitect.transit :as t]))

(defonce ws-chan (atom nil))

(def json-reader
  (t/reader :json))

(def json-writer
  (t/writer :json))

(defn receive-fact!
  [handler]
  (fn [fact]
    (->> fact
         .-data
         (t/read json-reader)
         handler)))

(defn send-fact!
  [fact]
  (if @ws-chan
    (->> fact
         (t/write json-writer)
         (.send @ws-chan))
    (throw (js/Error. "WebSocket is not available!"))))

(defn connect!
  [url receive-handler]
  (if-let [chan (js/WebSocket. url)]
    (do
      (set! (.-onmessage chan) (receive-fact! receive-handler))
      (reset! ws-chan chan))
    (throw (js/Error. "WebSocket connection failed!"))))
