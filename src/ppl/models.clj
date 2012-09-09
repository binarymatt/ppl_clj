(ns ppl.models
  ;(:require [lobos.migrations :as migrations])
  (:use [korma.db]
        [korma.core]
        ))

(def db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "ppl"
   :user "matt"
   :password ""})

(defdb dev db)
(defentity profiles)
(defentity users
  (has-one profiles  {:fk :user_id}))
