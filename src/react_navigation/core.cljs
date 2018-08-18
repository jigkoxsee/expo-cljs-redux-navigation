(ns react-navigation.core
  (:require [reagent.core :as r :refer [atom]]))

(defonce ReactNavigation (js/require "react-navigation"))
