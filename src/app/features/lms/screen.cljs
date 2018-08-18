(ns app.features.lms.screen
  (:require [reagent.core :as r :refer [atom]]
    [re-frame.core :as rf]
    [oops.core :refer [ocall]]
    [react-native.core :refer [view text]]))

(defn screen []
  [view
   [text "lms screen"]])
