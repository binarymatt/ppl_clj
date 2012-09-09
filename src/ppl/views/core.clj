(ns ppl.views.core
  (:require [ppl.templates :as templates])
  (:use [noir.core :only [defpage]]))

(defpage "/" []
  (templates/home :test))

