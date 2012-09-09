(ns lobos.migrations
  (:refer-clojure :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]]
               core
               schema)))


(def db-lite
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "./db.sqlite3"})
(def  db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "ppl"
   :user "matt"
   :password ""})

(comment
(defmigration add-authors-table
  (up [] (create 
           (table :authors (integer :id :primary-key )
             (varchar :username 100 :unique )
             (varchar :password 100 :not-null )
             (varchar :email 255))))
  (down [] (drop (table :authors ))))
)
(defmigration add-users
  (up [] (create 
           (table :users 
                  (integer :id :primary-key :auto-inc)
                  (integer :sign_in_count (default 0))
                  (timestamp :created_ts (default (now)))
                  (timestamp :updated_ts)
                  (boolean :admin (default false))
                  (varchar :email 256 :not-null)
                  (varchar :access_token 255)
                  (text :access_token_secret)
                  (varchar :provider 255)
            )))
  (down [] (drop (table :users))))
(defmigration add-profiles
  (up [] (create
           (table :profiles
                  (integer :id :primary-key :auto-inc)
                  (varchar :email 256)
                  (varchar :twitter 256)
                  (varchar :url 255)
                  (text :bio)
                  (timestamp :created_ts (default (now)))
                  (timestamp :updated_ts (default (now)))
                  (varchar :name 255)
                  (varchar :location 255)
                  (varchar :slug 255)
                  )))
  (down [] (drop (table :profiles)))
)
;(defmigration add-groups)
;(defmigration add-companies)
;(defmigration add-resources)

