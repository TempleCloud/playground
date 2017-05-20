(ns facts.core)

(-> (.getElementById js/document "content")
  (.-innerHTML)
  (set! "Lets see some awesome facts!"))
