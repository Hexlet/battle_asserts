(ns battle-asserts.issues.reverse-each-word
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given a string as input, reverse each word, but keep the word order the same.
                  Words are separated by whitespaces.")

(def signature
  {:input  [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [sentences (repeatedly 30 #(faker/sentence {:words-range [1 10]}))]
    (gen/tuple (gen/elements sentences))))

(def test-data
  [{:expected  "olleH ,ereht dna woh era ?uoy"
    :arguments  ["Hello there, and how are you?"]}
   {:expected "I’m Fine, Thanks. And You?"
    :arguments ["m’I ,eniF .sknahT dnA ?uoY"]}
   {:expected "ehT kciuq nworb xof spmuj revo eht yzal god"
    :arguments  ["The quick brown fox jumps over the lazy dog"]}])

(defn solution [string]
  (->>
   (s/split string #" ")
   (map clojure.string/reverse)
   (s/join " ")))
