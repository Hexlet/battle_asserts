(ns battle-asserts.issues.palindrome-anagram
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :medium)

(def description "Check if a string is an anagram of the palindrome.")

(defn arguments-generator []
  (letfn [(alphabet []
            (map char (range (int \a) (inc (int \z)))))
          (shuffled-palindrome []
                               (let [length (+ 2 (rand-int 6))
                                     first-half (repeatedly (quot (inc length) 2) #(rand-nth (alphabet)))
                                     second-half (subvec (vec (reverse first-half)) (rem length 2))]
                                 (s/join (shuffle (concat first-half second-half)))))
          (string []
                  (let [length (rand-int 8)]
                    (s/join (repeatedly length #(rand-nth (alphabet))))))]
    (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 shuffled-palindrome))
                            (gen/elements (repeatedly 50 string))]))))

(def test-data
  [{:expected true
    :arguments ["abcabc"]}
   {:expected true
    :arguments [""]}
   {:expected true
    :arguments ["a"]}
   {:expected true
    :arguments ["aa"]}
   {:expected true
    :arguments ["aab"]}
   {:expected true
    :arguments ["aabb"]}
   {:expected true
    :arguments ["aabbc"]}
   {:expected false
    :arguments ["ab"]}
   {:expected false
    :arguments ["aabbcd"]}
   {:expected false
    :arguments ["aaabbb"]}])

(defn solution [s]
  (->> s
       (frequencies)
       vals
       (filter odd?)
       count
       (>= 1)))