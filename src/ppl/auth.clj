(ns ppl.auth 
  (:require [clj-oauth2.client :as oauth2])
  (:use [cheshire.core :only (parse-string)])
  (:require [noir.session :as session])
)


(def github
  {
   :authorization-uri "https://github.com/login/oauth/authorize"
   :access-token-uri "https://github.com/login/oauth/access_token"
   :redirect-uri "http://localhost:8080/login/github/callback"
   :client-id "21258725b6a9d9131a69"
   :client-secret "e21258573bde5517c3bb73812665d08b4110f168"
   :scope ["user"]
   ;:access-query-param :access_token
   :grant-type "authorization_code"
  })

(def auth-req
  (oauth2/make-auth-request github "some-csrf"))

(defn access-token [req]
  (oauth2/get-access-token github req auth-req))

(defn github-user-email [access-token]
  (let [response (oauth2/get "https://api.github.com/user" {:query-params {:access_token access-token}} )]
    (get (parse-string (:body response)) "email")))

(defn github-user [access-token]
  (let [response (oauth2/get "https://api.github.com/user" {:query-params {:access_token access-token}} )]
    (parse-string (:body response))))

(defn login-user [id]
  (session/put! :user-id id)
  true)

(defn logged-in? []
  (when-let [id (session/get :user-id)]
    id))
;(defn current-user []
;  (users/find-by-id (session/get :user-id)))

;(defn ensure-logged-in []
;  (when-not (session/get :user-id)
;      ;(store-location)
;      (resp/redirect "/login")))

