(defproject ppl "0.1.0-SNAPSHOT"
            :description "FIXME: write this!"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [noir "1.2.1"]
                           [stencil "0.3.1"]
                           [postgresql/postgresql "9.1-901.jdbc4"]
                           [org.clojure/java.jdbc "0.2.3"]]
            :main ppl.server)