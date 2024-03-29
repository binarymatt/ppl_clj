(ns ppl.server
  (:require [noir.server :as server])
  (:use [metrics.ring.instrument :only (instrument)])
  )

(server/load-views-ns "ppl.views")
(server/add-middleware instrument)

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'ppl})))

