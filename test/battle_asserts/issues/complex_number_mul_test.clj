(ns battle-asserts.issues.complex-number-mul-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.complex-number-mul :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (string? (apply issue/solution v))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))