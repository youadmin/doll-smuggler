(ns knapsack.test.core
  (:use [knapsack.core])
  (:use [clojure.test])
  (:use [knapsack.test.inputs.dolls])
  (:use [knapsack.test.outputs.dolls])
)

;; First three tests exist to validate the main test collection
;; TEST equality of collections
(deftest check-master-collection-equality ;; Test the returned collection against expected 
  (is (= (sort-by :name master-expected) (sort-by :name (get-dolls master master-weight-restriction))))
)

(deftest test-max-of-value ;; Given a sequence of elements, return the one with the greatest value 
  (is (= 200 (:value (max-of master :value)) ))
)

(deftest test-max-of-weight ;; Given a sequence of elements, return the one with the greatest weight 
  (is (= 153 (:weight (max-of master :weight)) ))
)

(def path-inputs [(hash-map :name "ben" :weight 12 :value 100)])

(deftest test-path-summation-util ;; Given a sequence of elements, return the correct summation of the sequence 
  (is (= (hash-map :associated-set [(hash-map :name "ben" :weight 12 :value 100)] :weight 12 :value 100 ) (path-summation-util path-inputs) ))
)
