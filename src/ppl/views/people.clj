(ns ppl.views.people
  (:require [ppl.templates :as common])
  (:require [noir.session :as session])
  (:require [noir.response :as resp])
  (:use [noir.core :only [defpage pre-route defpartial]]
        [noir.server :only [wrap-route]])
  (:require [ppl.auth :as auth])
  (:use [korma.core :only [select where fields, insert, values, update, set-fields, with]])
  (:use [ppl.models :only [users profiles]])
  (:use [clavatar.core :only [gravatar]])
)
(defpartial user-item [{:keys [email name]}]
  [:li.user
    [:a {:href "#"} [:img {:src (gravatar email)}]]
    [:h3 name]])

(defpartial user-list [items]
    [:ul#todoItems.unstyled ;; set the id attribute
        (map user-item items)])
(defpage "/people" []
  (common/layout "People" :people
    (user-list (select users (with profiles)))
  )
)
