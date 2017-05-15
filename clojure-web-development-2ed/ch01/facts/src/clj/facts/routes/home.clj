(ns facts.routes.home
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io])
  (:require [facts.db.core :as db]
            [facts.layout :as layout]))

(defn home-page
  []
  (layout/render
    "home.html"
    {:facts (db/get-facts)}))

(defn about-page
  []
  (layout/render "about.html"))

(defn save-fact!
  [{:keys [params]}]
  (db/save-fact!
    (-> params
        (assoc :user_id 1)
        (assoc :timestamp (java.util.Date.))))
  (response/found "/"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/fact" request (save-fact! request))
  (GET "/about" [] (about-page)))

