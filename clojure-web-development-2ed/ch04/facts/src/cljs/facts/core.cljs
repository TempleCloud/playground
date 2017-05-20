(ns facts.core
  (:require [reagent.core :as reagent :refer [atom]]))

(defn home
  []
  [:div.row
    [:div.span12
     [message-form]]])

(defn message-form
  []
  (let [fields (atom {})]
    (fn []
      [:div.content
       [:p "side_1 value: " (:side_1 @fields)]
       [:p "side_2 value: " (:side_2 @fields)]
       [:div.form-group
        [:p "Front:"
         [:input.form-control
          {:type :text
           :name :side_1
           :value (:side_1 @fields)
           :on-change #(swap! fields
                              assoc :side_1 (-> % .-target .-value))
           }]]
        [:p "Back:"
         [:textarea.form-control
          {:rows 4
           :cols 50
           :name :side_2
           :value (:side_2 @fields)
           :on-change #(swap! fields
                              assoc :side_2 (-> % .-target .-value))
           }]]
        [:input.btn.btn-primary {:type :submit :value "add fact"}]]]
      )))

(reagent/render
  [home]
  (.getElementById js/document "content"))
