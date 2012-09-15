(ns ppl.views.account
  (:require [ppl.templates :as common])
  (:require [noir.session :as session])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [noir.response :only [redirect]])
  (:require [stencil.core :refer [render-file]])
  (:require [clj-oauth2.client :as oauth2])
  (:require [ppl.auth :as ppl_auth])
  ;(:require [korma.db :refer [select]])
  (:use [korma.core :only [select where fields, insert, values, update, set-fields]])
  ;(:require [ppl.models :refer [users]])
  (:use [ppl.models :only [users]])
  ;(:require [korma.db] 
  ;          [korma.core] 
  ;          [ppl.models])
  ;(:use [ppl.views.auth :only (auth-req access-token github-user-email)])
)
(defn create-user [github-map token]
  ;returns a user map that can be inserted into the database
  {:email (get github-map "email") :access_token token :provider "github"})

(defn check-user [email]
  (seq (select users (fields :id)(where {:email email}))))

(defn update-token [id token]
  (if-not (seq (select users (fields :id)(where {:access_token token})))
    (update users (set-fields {:access_token token})(where {:id id}))
    )
  (id)
  )


(defpage "/signup/" []
         (common/layout "Signup" :signup
           [:div.span4 
            [:a {:href "/login/github"} "login with github"]]))

(defpage "/login/" []
         (common/layout "Login" :login))
(defpage "/login/github" []
         (redirect (:uri ppl_auth/auth-req)))

(defpage "/login/github/callback" {:as req}
         (let [token (get (ppl_auth/access-token req) :access-token)]
           (session/put! :github-token token)
           (let [guser (ppl_auth/github-user token)]
             ;new user? create and login
             ;
            (let [current-user (check-user (get guser "email"))]
            (ppl_auth/login-user (if current-user
              (update-token current-user token)
              (insert users (values (create-user guser token)))
            )))))
         (redirect "/home")

           ;login said user
           
           ;redirect to home page
           )
  
(defpage "/details" []
         (ppl_auth/github-user-email (session/get :github-token)))

(defpage "/profile" []
  (when-not (ppl_auth/logged-in?)
    (redirect "/login")))
