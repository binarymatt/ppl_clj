(defproject ppl "0.1.0-SNAPSHOT"
            :description "FIXME: write this!"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [noir "1.3.0-beta2"]
                           [stencil "0.3.1"]
                           [hiccup "1.0.1"]
                           ; Database
                           [lobos "1.0.0-SNAPSHOT"]
                           [postgresql/postgresql "9.1-901.jdbc4"]
                           [org.clojure/java.jdbc "0.2.3"]
                           [korma "0.3.0-beta11"]
                           ; Metrics
                           [metrics-clojure "0.8.0"]
                           [metrics-clojure-ring "0.8.0"]
                           ; Others
                           [ring-anti-forgery "0.2.0"]
                           [clj-oauth2-ls "0.3.0"]
                           [cheshire "4.0.2"]
                           ]
            :cljsbuild {:builds [{}]}
            :main ^{:skip-aot true} ppl.server)
