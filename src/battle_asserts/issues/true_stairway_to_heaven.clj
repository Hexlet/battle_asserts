(ns battle-asserts.issues.true-stairway-to-heaven
  (:require [clojure.test.check.generators :as gen]))

(def level :hard)

(def description "`N` dicks randomly spread out of `M` stairs, there can be as many dicks as you want on one step. We gotta go down these stairs.
                  Every time you step on a stair-step with dicks, the infame number increases by the number of dicks.
                  You can go down one or two steps at a time. Write a function to descend the stairs minimizing the infame number.
                  The function receives an array with the number of dicks on each step and returns the minimized infame number.
                  Powered by Eugene Zaytsev.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 0 12) 8 40)))

(def test-data
  [{:expected 0 :arguments [[0 1 0 1]]}
   {:expected 0 :arguments [[1 0 1 0 1]]}
   {:expected 6 :arguments [[1 0 3 5 10 0 11 1]]}
   {:expected 17 :arguments [[0 11 6 8 1 4 10 9]]}
   {:expected 30 :arguments [[9 11 1 5 6 6 5 4 9 12]]}])

(defn iter-step [steps]
  (let [len (count steps)]
    (cond
      (= len 1) (steps 0)
      (= len 2) (min (steps 1) (steps 0))
      :else
      (letfn [(iter [i acc1 acc2] (if (= i len)
                                    (min acc1 acc2)
                                    (iter (inc i) (+ (steps i) (min acc1 acc2)) acc1)))]
        (iter 2 (steps 1) (steps 0))))))

(defn solution [steps-map]
  (iter-step steps-map))
