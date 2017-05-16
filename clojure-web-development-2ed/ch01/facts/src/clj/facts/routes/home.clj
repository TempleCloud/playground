(ns facts.routes.home
  (:require [clojure.java.io :as io]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response])
  (:require [facts.db.core :as db]
            [facts.layout :as layout]))

(defn home-page
  [{:keys [flash]}]
  (layout/render
    "home.html"
    (merge
      {:facts (db/get-facts)}
      (select-keys flash [:side_1
                          :side_2
                          :errors]))))

(defn about-page
  []
  (layout/render "about.html"))

(defn validate-fact
  [params]
  (first
   (b/validate
    params
    :side_1  [v/required [v/min-count 1]]
    :side_2  [v/required [v/min-count 1]])))

(defn save-fact!
  [{:keys [params]}]
  (if-let [errors (validate-fact params)]
    (-> (response/found "/")
      (assoc :flash (assoc params :errors errors)))
    (do
      (db/save-fact!
        (-> params
            (assoc :user_id 1)
            (assoc :timestamp (java.util.Date.))))
      (response/found "/"))))

(defroutes
  home-routes
  (GET "/" request (home-page request))
  (POST "/fact" request (save-fact! request))
  (GET "/about" [] (about-page)))

