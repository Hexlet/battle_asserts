(ns battle-asserts.issues.capitalized-words-number
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as str]
            [faker.generate :as f]))

(def level :easy)

(def tags ["strings"])

(def description
  {:en "Find number of words starting with capital letter"
   :ru "Подсчитайте количество слов, начинающихся с заглавной буквы."})

(def signature
  {:input [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple
   (gen/let [words (gen/vector (gen/elements (f/sentences {:n 2})) 1 9)]
     (gen/return (str/join " " words)))))

(def test-data
  [{:expected 0
    :arguments ["lorem ipsum."]}
   {:expected 2
    :arguments ["Lorem ipsum, Hello"]}
   {:expected 2
    :arguments ["lorem, ipsum Hello CaeSar"]}
   {:expected 0
    :arguments ["loRem ipSum"]}
   {:expected 3
    :arguments ["Lorem Ipsum Caesar"]}])

(defn solution [s]
  (->>
   (str/split s #" ")
   (filter #(when-let [letter (get % 0)] (Character/isUpperCase letter)))
   (count)))
