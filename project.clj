(defproject shopping-cart "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :profiles {:dev {:dependencies [[lambdaisland/kaocha "1.60.977"]]}}
  :aliases {"kaocha" ["run" "-m" "kaocha.runner"]}
  :repl-options {:init-ns shopping-cart.core}
  :main shopping-cart.core
  :aot [shopping-cart.core])
