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

;; TEST expected value in the knapsack
(deftest check-master-total-value ;; Test the total value of returned collection 
	(is (= master-expected-total-value (reduce + (map :value (get-dolls master master-weight-restriction)))))
)

;; TEST expected weight of the knapsack
(deftest check-master-total-weight ;; Test the total weight of returned collection 
  (is (= master-expected-total-weight (reduce + (map :weight (get-dolls master master-weight-restriction)))))
)
