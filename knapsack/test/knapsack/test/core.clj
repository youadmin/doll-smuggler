(ns knapsack.test.core
  (:use [knapsack.core])
  (:use [clojure.test])
  (:use [knapsack.test.inputs.dolls])
)

(deftest check-collection-equality ;; Test the returned collection against expected 
  (is (= output (sort-by :name (get-dolls input weight))))
)

(deftest check-total-value ;; Test the total value of returned collection 
  (is (= 25 (reduce + (map :value (get-dolls input weight)))))
)

(deftest check-total-weight ;; Test the total weight of returned collection 
  (is (= 23 (reduce + (map :weight (get-dolls input weight)))))
)
