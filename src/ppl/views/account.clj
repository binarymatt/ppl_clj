(ns ppl.views.account
  (:require [ppl.templates :as common])
  (:require [noir.session :as session])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [noir.response :only [redirect]])
  (:require [stencil.core :refer [render-file]])
  (:require [clj-oauth2.client :as oauth2])
  (:require [ppl.auth :as ppl_auth])
  ;(:use [ppl.views.auth :only (auth-req access-token github-user-email)])
)
(defn create-user [github-map token]
  ;returns a user map that can be inserted into the database
  {:email (get github-map "email") :access_token token :provider "github"})

(defn check-user [email]
  (seq (select users (fields :id)(where {:email email}))))

(defpage "/signup/" []
         (common/layout "Signup" :signup
           [:div.span4 "signup"]))

(defpage "/login/github" []
         (redirect (:uri ppl_auth/auth-req)))

(defpage "/login/github/callback" {:as req}
         (let [token (ppl_auth/access-token req)]
           (session/put! :github-token token)
           (let [github-user (ppl_auth/github-user token)]
            ;create user if they don't exist
            (def current-user (check-user (get gitub-user "email")))
            (if current-user)
              (current-user)
              (insert users (values (create-user github-user token)))
            ))
           ;login said user
           
           ;redirect to home page
           )
  )
(defpage "/details" []
         (ppl_auth/github-user-email ((session/get :github-token) :access-token)))

(defpage "/profile" [])
