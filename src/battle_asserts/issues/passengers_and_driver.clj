(ns battle-asserts.issues.passengers-and-driver
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "A typical car can hold three passengers and one driver, allowing four people to travel somewhere.
                  Given `n` number of people, calculate how many cars are needed to seat everyone comfortably.")

(def signature
  {:input [{:argument-name "peoples" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 1 30)))

(def test-data
  [{:expected 0 :arguments [0]}
   {:expected 4 :arguments [13]}
   {:expected 1 :arguments [4]}
   {:expected 3 :arguments [9]}])

(defn solution [peoples]
  (int (Math/ceil (/ peoples 4))))
