(ns facts.core
  (:require [reagent.core :as reagent :refer [atom]]
            [ajax.core :refer [DELETE GET POST]]))

(defn errors-component
  [errors id]
  (when-let [error (id @errors)]
    [:div.alert.alert-danger (clojure.string/join error)]))


(defn get-facts
  [facts]
  (GET
    "/fact"
    {:headers {"Accept" "application/transit+json"}
     :handler #(reset! facts (vec %))}))

(defn fact-list
  [facts]
  [:ul.content
   (for
     [{:keys [timestamp side_1 side_2]} @facts]
      ^{:key timestamp}
      [:li
       [:time (.toLocaleString timestamp)]
       [:p side_1]
       [:p side_2]])])

(defn save-fact!
  [fields errors facts]
  (POST "/fact"
        {:format :json
         :headers {"Accept"       "application/transit+json"
                   "x-csrf-token" (.-value (.getElementById js/document "token"))}
         :params @fields
         :handler #(do
                     ;(.log js/console (str "response:" %))
                     (reset! errors nil)
                     (swap! facts conj (assoc @fields :timestamp (js/Date.)))
                     )
         :error-handler #(do
                           (.error js/console (str "error:" %))
                           (reset! errors (get-in % [:response :errors])))}))

(defn fact-form
  [facts]
  (let [fields (atom {})
        errors (atom nil)]
    (fn []
      [:div.content
       ;[:p "side_1 value: " (:side_1 @fields)]
       ;[:p "side_2 value: " (:side_2 @fields)]
       [:div.form-group
        [:p "Front:"
         [:input.form-control
          {:type :text
           :name :side_1
           :value (:side_1 @fields)
           :on-change #(swap! fields
                              assoc :side_1 (-> % .-target .-value))}]]
        [errors-component errors :side_1]
        [:p "Back:"
         [:textarea.form-control
          {:rows 4
           :cols 50
           :name :side_2
           :value (:side_2 @fields)
           :on-change #(swap! fields
                              assoc :side_2 (-> % .-target .-value))}]]
        [errors-component errors :side_2]
        [:input.btn.btn-primary {:type :submit
                                 :on-click #(save-fact! fields errors facts)
                                 :value "add fact"}]
        [errors-component errors :server-error]]])))

;; (defn home
;;   []
;;   [:div.row
;;     [:div.span12
;;      [fact-form]]])

(defn home
  []
  (let [facts (atom nil)]
    (get-facts facts)
    (fn []
      [:div
       [:div.row
         [:div.span12
          [fact-form facts]]]
       [:div.row
        [:div.span12
         [fact-list facts]]]
       ])))

(reagent/render
  [home]
  (.getElementById js/document "content"))
