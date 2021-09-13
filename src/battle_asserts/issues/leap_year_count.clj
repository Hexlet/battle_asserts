(ns battle-asserts.issues.leap-year-count
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["date-time"])

(def description
  {:en "Given a range of years as array of two numbers, return the number of leap years there are within the range (inclusive).
                  Leap years are all divisible by 4, with the exception of centuries, which are not divisible by 400."
   :ru "Дан временной отрезок (в годах), представленный массивом из двух чисел. Определите все високосные года в этом промежутке (включительно).  Любой год, который делится на 4 без остатка, является високосным годом. Тем не менее, есть еще небольшая особенность, которая должна быть учтена например, григорианский календарь предусматривает, что год, который делится без остатка на 100 является високосным годом только в том случае, если он также без остатка делится на 400."})

(def signature
  {:input [{:argument-name "years" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/tuple (gen/choose 1700 2000) (gen/choose 2000 2300))))

(def test-data
  [{:expected 2 :arguments [[1980 1984]]}
   {:expected 7 :arguments [[2000 2025]]}
   {:expected 49 :arguments [[1800 2000]]}])

(defn leap-year? [year]
  (letfn [(divisible? [a b] (zero? (mod a b)))]
    (and (divisible? year 4) (or (not (divisible? year 100)) (divisible? year 400)))))

(defn solution [[left-border right-border]]
  (count (filter leap-year? (range left-border (inc right-border)))))
