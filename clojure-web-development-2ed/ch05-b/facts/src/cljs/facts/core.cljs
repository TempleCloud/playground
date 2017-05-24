(ns facts.core
  (:require [reagent.core :as reagent :refer [atom]]
            [ajax.core :refer [DELETE GET]]
            [facts.ws :as ws]))

(defn errors-component
  [errors id]
  (when-let [error (id @errors)]
    [:div.alert.alert-danger (clojure.string/join error)]))


(defn delete-fact!
  [id facts]
  (DELETE "/fact"
          {:headers {"Accept"       "application/transit+json"
                     "x-csrf-token" (.-value (.getElementById js/document "token"))}
           :params {:id id}
           :handler (fn
                      [response]
                      (do
                        (.log js/console (str "DELETE response:" response))
                        (if (:deleted response)
                          (reset! facts (remove (fn [fact] (= id (:id fact))) @facts))
                          facts)))}))

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
     [{:keys [timestamp id side_1 side_2]} @facts]
      ^{:key timestamp}
      [:li
       [:time (.toLocaleString timestamp)]
       [:p side_1]
       [:p side_2]
       [:input.btn.btn-primary {:type :submit
                                :on-click #(delete-fact! id facts)
                                :value "delete fact"}]])])

(defn fact-form
  [fields errors]
  [:div.content
   [:div.form-group
    [errors-component errors :side_1]
    [:p "Front:"
     [:input.form-control
      {:type      :text
       :on-change #(swap! fields assoc :side_1 (-> % .-target .-value))
       :value (:side_1 @fields)}]]
    [errors-component errors :side_2]
    [:p "Back:"
     [:textarea.form-control
      {:rows  4
       :cols  50
       :value (:side_2 @fields)
       :on-change #(swap! fields assoc :side_2 (-> % .-target .-value))}]]
    [:input.btn.btn-primary
     {:type     :submit
      :on-click #(ws/send-fact! [:facts/add-fact @fields] 8000)
      :value    "add fact"}]]])


(defn response-handler
  [facts fields errors]
  (fn [{[_ fact] :?data}]
    (if-let [response-errors (:errors fact)]
      (reset! errors response-errors)
      (do
        (reset! errors nil)
        (reset! fields nil)
        (swap! facts conj fact)))))

(defn home
  []
  (let [facts  (atom nil)
        errors (atom nil)
        fields (atom nil)]
    (ws/start-router! (response-handler messages fields errors))
    (get-facts facts)
    (fn []
      [:div
       [:div.row
        [:div.span12
         [fact-form fields errors]]]
       [:div.row
        [:div.span12
         [fact-list facts]]]])))


(reagent/render
  [home]
  (.getElementById js/document "content"))
