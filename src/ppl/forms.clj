(ns ppl.forms)

(defpartial user-fields [{:keys [firstname lastname]}]
  (label "firstname" "First name: ")
  (text-field "firstname" firstname)
  (label "lastname" "Last name: ")
  (text-field "lastname" lastname))
