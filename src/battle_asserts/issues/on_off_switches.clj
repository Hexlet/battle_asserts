(ns battle-asserts.issues.on-off-switches
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function that returns how many possible outcomes can come from a certain number of switches (on / off).")

(def signature
  {:input  [{:argument-name "switches" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 2 :arguments [1]}
   {:expected 8 :arguments [3]}
   {:expected 1024 :arguments [10]}])

(defn arguments-generator []
  (gen/tuple (gen/choose 1 15)))

(defn solution [switches]
  (int (Math/pow 2 switches)))
