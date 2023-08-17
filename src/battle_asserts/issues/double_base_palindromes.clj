(ns battle-asserts.issues.double-base-palindromes
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["bits-operation"])

(def description
  {:en "The decimal number 585 is 1001001001 in binary. It is palindromic in both bases. Find n-th palindromic number."
   :ru "Десятичное число 585 в двоичном виде 1001001001. Оно палиндромично в обоих случаях. Найдите n-е десятичное число, которое будет палиндромом по обоим основаниям."})

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 15)))

(def test-data
  [{:expected 5
    :arguments [3]}
   {:expected 1
    :arguments [1]}
   {:expected 99
    :arguments [7]}
   {:expected 39993
    :arguments [15]}])

(defn solution [num]
  (letfn [(palindromic? [n]
            (= (seq (str n))
               (reverse (str n))))
          (binary-palindromic? [n]
            (palindromic? (Integer/toBinaryString n)))]
    (->> (range 1 10000000)
         (lazy-seq)
         (filter #(and (palindromic? %)
                       (binary-palindromic? %)))
         (take num)
         (last))))
