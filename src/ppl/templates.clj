(ns ppl.templates
  (:require [ppl.auth :as auth])
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.element :only [link-to]]))


; Utils -----------------------------------------------------------------------
(defn include-less [path]
  [:link {:rel "stylesheet/less" :type "text/css" :href path}])


; Layout ----------------------------------------------------------------------
(defpartial base [title body-id & content]
  (html5
    [:head
     [:title title " / ppl"]
     (include-css "/bootstrap/styles/bootstrap.css")
     (include-css "/bootstrap/styles/bootstrap_responsive.css")
     (include-less "/styles/style.less")
     (include-js "/bootstrap/scripts/bootstrap.js")
     (include-js "/scripts/less.js")]
    [:body {:id (name body-id)}
     [:div#container content]
     ]))

(defpartial layout [title body-id & content]
            (html5
              [:head
               [:meta {:charset "utf-8"}]
               [:title title " / ppl"]
               (include-css "/css/bootstrap.css")
               (include-css "/css/bootstrap-responsive.css")
               (include-css "/css/font-awesome.css")
               "<!--[if lt IE 9]>"
               (include-js "http://html5shim.googlecode.com/svn/trunk/html5.js")
               "<![endif]-->"
               ]
              [:body {:id (name body-id)}
                [:header
                [:div.navbar.navbar-static-top
                 [:div.navbar-inner
                  [:div.container
                   [:a.brand {:href "/"} "Nashvl"]
                   [:ul.nav
                    [:li [:a {:href "/people"} "People"]]
                    [:li [:a {:href "/companies"} "Companies"]]
                    [:li [:a {:href "/groups"} "Groups"]]
                    [:li [:a {:href "/jobs"} "Jobs"]]
                    ]
                    (if (auth/logged-in?)
                      [:ul.nav.pull-right
                        [:li [:a {:href "/profile"} "Profile"]]
                        [:li [:a {:href "/logout"} "Logout"]]
                      ]
                      [:ul.nav.pull-right
                        [:li [:a {:href "/login"} "Login"]]
                      ]
                    )
                   ]
                  ]
                 ]
                ]
               [:div.container
                content]
                (include-js "/js/jquery-1.8.0.min.js")
                (include-js "/js/bootstrap.min.js")
               ]))
; Pages -----------------------------------------------------------------------
(defn home [content]
  (base "Home" :home content))
