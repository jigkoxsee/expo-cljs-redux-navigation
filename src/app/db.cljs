(ns app.db
  (:require [clojure.spec.alpha :as s]))

;; spec of app-db
(s/def ::greeting string?)
(s/def ::app-db
  (s/and
    ;(s/keys :req-un [::version])
    (s/keys :req-un [::greeting])
    (s/keys :req-un [::navigation])))



(def navigation
  {:key "StackRouterRoot",
   :isTransitioning false,
   :index 0
   :routes
   [{:routes
             [{:key "left", :routeName "left", :params {:value "passing-params"}}
              {:key "right",
               :routeName "right",
               :params {:value "passing-params"}}],
     :index 0,
     :isTransitioning false,
     :routeName "home",
     :key "id-1528647757031-0"}]})

;; initial state of app-db
(def app-db
  {:greeting "Hello Expo!"
   :navigation (clj->js navigation)})
