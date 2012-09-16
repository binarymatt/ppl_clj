(ns ppl.views.account
  (:require [ppl.templates :as common])
  (:require [noir.session :as session])
  (:require [noir.response :as resp])
  (:use [noir.core :only [defpage pre-route]]
        [noir.server :only [wrap-route]])
  (:require [ppl.auth :as auth])
  (:use [korma.core :only [select where fields, insert, values, update, set-fields]])
  (:use [ppl.models :only [users profiles]])
  (:use [slugify.core :only [slugify]])
)

(defn create-user [github-map token]
  (let [user (insert users (values {:email (get github-map "email") :access_token token :provider "github"}))]
    (insert profiles (
      values {
        :user_id (get user :id) 
        :name (get github-map "name") 
        :bio (get github-map "bio") 
        :url (get github-map "url")
        :slug (slugify (get github-map "name"))
        :location (get github-map "location")
        :github_name (get github-map "login")
      }))
    (get user :id)))

(defn check-user [email]
  (get (first (select users (fields :id)(where {:email email}))) :id))

(defn update-token [id token]
  (if-not (seq (select users (fields :id)(where {:access_token token})))
    (update users (set-fields {:access_token token})(where {:id id}))
    )
  id
  )


(defpage "/signup" []
         (common/layout "Signup" :signup
           [:div.span4 
            [:a {:href "/login/github"} [:i.icon-github] "signup with github"]]))

(defpage login "/login" []
  (common/layout "Login" :login
    [:div.span4 
      [:a.btn.btn-primary {:href "/login/github"} [:i.icon-github.icon-large] " Login with Github"]
     ]))
(defpage "/login/github" []
         (resp/redirect (:uri auth/auth-req)))

(defpage "/login/github/callback" {:as req}
         (let [token (get (auth/access-token req) :access-token)]
           (session/put! :github-token token)
           (let [guser (auth/github-user token)]
             ;new user? create and login
             ;
            (let [current-user (check-user (get guser "email"))]

            (auth/login-user (if current-user
              (update-token current-user token)
              (create-user guser token)
            )))))
         (resp/redirect "/")

           ;login said user
           
           ;redirect to home page
           )
(defpage "/logout" []
  (session/remove! :user-id)
  (session/remove! :github-token)
  (resp/redirect "/login")
)
(defpage "/details" []
         (auth/github-user-email (session/get :github-token)))

(defpage profile "/profile" []
  (let [user (auth/current-user)]
  (common/layout "Profile" :profile
                 [:div (get user :email)])
))
(pre-route "/profile*" {}
  (auth/login-required!))


