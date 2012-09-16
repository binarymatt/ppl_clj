(ns config.database
  (:use
   [korma.db]
   [korma.core]
   ))

(def db-uri (java.net.URI. (System/getenv "DATABASE_URL")))
(def user-and-password (clojure.string/split (.getUserInfo db-uri) #":")) 

(defdb db (postgres {
               :db (clojure.string/replace (.getPath db-uri) "/" "") 
               :user (first user-and-password) 
               :password (seq (rest user-and-password)) 
               :host (.getHost db-uri) 
               :port (.getPort db-uri)}))
