(ns battle-asserts.issues.circle-point
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math"])

(def description
  {:en "On the flat there is a circle centered at zero point (0, 0) with radius r. Does the point with coordinates x and y belongs to this circle. Tip: to solve the problem, you need to find the hypotenuse."
   :ru "Дана точка с координатами `x` и `y` и окружность с радиусом `r` в точке (0, 0). Проверьте, находится ли точка в окружности. Подсказка: чтобы решить задачу, вам нужно найти гипотенузу."})

(def signature
  {:input  [{:argument-name "x" :type {:name "integer"}}
            {:argument-name "y" :type {:name "integer"}}
            {:argument-name "r" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose -50 50) (gen/choose -50 50) (gen/choose 1 100)))

(def test-data
  [{:expected false
    :arguments [10 3 5]}
   {:expected true
    :arguments [-50 0 100]}
   {:expected true
    :arguments [-1 2 5]}
   {:expected true
    :arguments [-50 50 100]}
   {:expected false
    :arguments [-1 3 1]}])

(defn solution [x y radius]
  (let [hypotenuse  (Math/sqrt (+ (* x x)
                                  (* y y)))] (<= hypotenuse radius)))

