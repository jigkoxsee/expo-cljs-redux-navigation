(ns app.navigation
  (:require
    [reagent.core :as r :refer [atom]]
    [re-frame.core :as rf]
    [oops.core :refer [ocall oget]]
    [cljs-react-navigation.reagent :as rnav]
    [expo.core :refer [ionicons]]
    [app.features.lms.screen :as lms]
    [react-native.core :refer [view text image touchable-opacity BackAndroid]]))


(defn random-color []
  (js* "'#'+('00000'+(Math.random()*(1<<24)|0).toString(16)).slice(-6)"))

(defn main-screen [opts]
  (let [greeting (rf/subscribe [:get-greeting])]
    (fn [opts2]
      (let [push (get-in opts2 [:navigation :navigate] #(prn :navigate-failed %1))
            getParams (get-in opts2 [:navigation :getParam])]
        [view {:style {:flex-direction "column" :align-items "center"}}
         [image {:source (js/require "./assets/images/icon-sq.png")
                 :style {:width 200
                         :height 200}}]
         [text {:style {:font-size 25 :font-weight "100" :margin-bottom 20 :text-align "center" :color (random-color)}} @greeting]
         [ionicons {:name "ios-arrow-down" :size 60 :color "green"}]

         [touchable-opacity {:style {:background-color "#999" :padding 10 :border-radius 5}
                             :on-press #(push "first" (clj->js {:value :passing-params}))}
          [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "first"]]

         [touchable-opacity {:style {:background-color "#999" :padding 10 :border-radius 5}
                             :on-press #(push "third" (clj->js {:value :passing-params}))}
          [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "third"]]]))))

(defn left-screen []
  (fn [opts]
    (let [push (get-in opts [:navigation :navigate] #(prn :navigate-failed %1))
          getParams (get-in opts [:navigation :getParam])]
      [view
       [text "left!!!!!!!!!!!!!"]
       [touchable-opacity {:style {:background-color "#999" :padding 10 :border-radius 5}
                           :on-press #(push "third" (clj->js {:value :passing-params}))}
        [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "first"]]])))

(defn right-screen []
  (fn []
    [view
     [text "right~~~~~~~~~~~~~"]
     [text "right~~~~~~~~~~~~~"]]))



;;------------------


(def tab-router
  {:left  {:screen (rnav/tab-screen left-screen {:title "left" :headerTitle "left header"})}
   :right {:screen (rnav/tab-screen right-screen {:title "right"})}})

(def tab (rnav/bottom-tab-navigator tab-router nil))


;;; ---- tab ---

(def router {:home   {:screen tab}
             :first  {:screen (rnav/stack-screen main-screen {:title "Home"})}
             :third  {:screen (rnav/stack-screen lms/screen {:title "lms"})}})

(def stack (rnav/stack-navigator router)); {:headerMode "none"}))

;;------------------

(defn navigate [action state]
  (let [new-state (stack.router.getStateForAction action @state)]
    (rf/dispatch [:set-navigation new-state])))

(defn back-navigation
  []
  (let [nav-state (rf/subscribe [:get-navigation])
        stack-index (oget @nav-state "index")]
    (prn :stack-index stack-index)
    (navigate #js {:type "Navigation/BACK"} nav-state)
    (not= stack-index 0)))

(defn register-back-button-handler
  []
  (.addEventListener BackAndroid "hardwareBackPress" back-navigation))
