(ns ppl.views.companies
  (:require [ppl.templates :as common])
  (:require [noir.session :as session])
  (:require [noir.response :as resp])
  (:use [noir.core :only [defpage pre-route defpartial]])
  (:require [ppl.auth :as auth])
  (:use [korma.core :only [select where fields, insert, values, update, set-fields, with]])
  (:use [ppl.models :only [users profiles]])
)

(defpage "/companies" []
         )
