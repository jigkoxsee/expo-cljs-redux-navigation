(ns app.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :as rf]
              [oops.core :refer [ocall]]
              [react-native.core :refer [Alert image view text touchable-opacity]]
              [expo.core :refer [Expo ionicons]]
              [react-navigation.core :as rnav]
              [cljs-react-navigation.reagent :as cnav]
              [app.navigation :as app-nav]
              [app.handlers]
              [app.subs]))

(defn navigate [state]
  (fn [action]
    (let [new-state (app-nav/stack.router.getStateForAction action @state)]
      (prn :nav-action (js->clj action))
      (rf/dispatch [:set-navigation new-state]))))

(defn navigation [state]
  (clj->js {:dispatch (navigate state)
            :state @state
            :addListener (fn [evn handler] nil)}))

(defn app-root []
  (let [nav-state (rf/subscribe [:get-navigation])]
    [:> app-nav/stack {:navigation (navigation nav-state)}]))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (app-nav/register-back-button-handler)
  (ocall Expo "registerRootComponent" (r/reactify-component app-root)))
