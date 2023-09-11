(ns battle-asserts.issues.garland-word
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def tags ["strings"])

(def description
  {:en "Write function that, given a lowercase word,
        returns the degree of the word if it's a garland word and 0 otherwise.
        A garland word is one that starts and ends with the same N letters in the same
        order, for some N greater than 0, but less that the length of the word."
   :ru "Напишите функцию, которая принимает слово в нижнем регистре,
        и возвращает степень слова, если это слово-гирлянда и 0 в противном случае.
        Слово-гирлянда - такое слово, которое начинается и заканчивается одними и теми же N буквами
        расположенными в одном и том же порядке. N больше 0, но меньше длины слова."})

(def signature
  {:input  [{:argument-name "word" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(def alphabet
  (map char (range (int \a) (inc (int \z)))))

(defn- garland-word [garlang-length]
  (format "%1$s%2$s%1$s"
          (s/join (repeatedly garlang-length #(rand-nth alphabet)))
          (s/join (repeatedly (rand-int 5) #(rand-nth alphabet)))))

(defn- corner-garland-word [garlang-length]
  (format "%1$c%2$s%1$c%2$s%1$c"
          (rand-nth alphabet)
          (s/join (repeatedly garlang-length #(rand-nth alphabet)))))

(defn arguments-generator []
  (gen/tuple (gen/one-of [(gen/elements (faker/words {:lang :en :n 50}))
                          (gen/bind (gen/choose 1 20) #(gen/return (garland-word %)))
                          (gen/bind (gen/choose 1 20) #(gen/return (corner-garland-word %)))])))

(def test-data
  [{:expected  2
    :arguments ["onion"]}
   {:expected  0
    :arguments ["aab"]}
   {:expected  1
    :arguments ["ceramic"]}
   {:expected 0
    :arguments ["programmer"]}
   {:expected 4
    :arguments ["alfalfa"]}
   {:expected 4
    :arguments ["abracadabra"]}
   {:expected 5
    :arguments ["undergrounder"]}])

(defn solution [word]
  (nth (filter #(= (take % word) (take-last % word))
               (range (dec (count word)) 0 -1)) 0 0))
