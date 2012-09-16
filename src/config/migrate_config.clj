;;
;; Drift migrations configuration template for Postgres and Korma
;;
;; By Paul Legato (pjlegato at gmail dot com)
;;
;; Requires Postgresql >= 9.0 (for IF NOT EXISTS clause)
;;
(ns config.migrate-config
  (:use [korma.db korma.core])
  (:use [config.database])
  (:require [drift-db.core :as drift-db]
            [drift-db.migrate :as drift-db-migrate])
)




(defn- maybe-create-schema-table
  "Creates the schema table if it doesn't already exist."
  [args]
  (exec-raw "CREATE TABLE IF NOT EXISTS schema_version (version INTEGER NOT NULL, created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now())"))

(defn current-db-version []
  (maybe-create-schema-table)
  (or (:version (first (select :schema_version (fields :version) (order :created_at :DESC) (limit 1)))) 0))

(defn update-db-version [version]
  (insert :schema_version (values {:version version})))

(defn migrate-config []
  { :directory "/src/migrations"
   ;:ns-content "\n (:use [korma.db] [korma.core])"
   :namespace-prefix "migrations"
   :init #(drift-db/init-flavor postgresql)
   :current-version drift-db-migrate/current-version
   :update-version drift-db-migrate/update-version
   :ns-content "\n  (:use drift-db.core)"
   ;:init maybe-create-schema-table
   ;:current-version current-db-version
   ;:update-version update-db-version 
   })
