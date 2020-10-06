(ns battle-asserts.issues.smallest-second-number-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [clojure.string :as s]
            [battle-asserts.issues.smallest-second-number :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (number? (apply issue/solution v))))

(ct/defspec spec-signature
  20
  (prop/for-all [v (issue/arguments-generator)]
                (true? (h/generate-signatures issue/signature v))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))

(deftest test-data-tests
  (h/generate-data-tests issue/test-data issue/signature))