(ns ppl.models
  ;(:require [lobos.migrations :as migrations])
  (:use [korma.db]
        [korma.core]
        )
  (:use [config.database]))

(defentity profiles)
(defentity users
  (has-one profiles  {:fk :user_id}))
