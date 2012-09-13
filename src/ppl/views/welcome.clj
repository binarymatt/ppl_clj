(ns ppl.views.welcome
  (:require [ppl.templates :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]])
  (:use [stencil.core :only [render-file]]))

(defpage "/" []
         (common/layout
           [:p "Welcome to ppl"]))

(defpage "/mustache" []
  (render-file "templates/layout"
             {:name "Donald"}))

(defpage "/home" []
         (common/layout
           ))
