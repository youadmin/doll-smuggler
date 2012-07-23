(ns knapsack.core
	(:use [clojure.set])
	(:gen-class)
)

;; helper method to build a map/summary of each knapsack path.
;; output properties include the total weight and value plus the target associated set.
(defn path-summation-util [set-of-tags] 
	(do 
		(if (not-empty set-of-tags)
			(hash-map :weight (reduce + (map :weight set-of-tags)), :value (reduce + (map :value set-of-tags)), :associated-set set-of-tags)
			(hash-map :weight 0 :value 0 :associated-set set-of-tags)
		)
	)
)

;; find the max of 'input' given 'fcn-apply', the function to apply
(defn max-of [input fcn-apply]

	(letfn [
		(inner-max [input maximum]
			(if (not-empty input)
				(recur (rest input)
							(if (> (fcn-apply maximum) (fcn-apply (first input)))
								maximum
								(first input)
							)
				 )
				maximum
			)
		)
	](inner-max (next input) (first input) ))
)

;; Basic algorithm...
;; The powerset is all subsets of a given set, including the empty and complete set (original argument)
;; The recusion stops when the argument list is empty.
;; We start with the argument list and recursively work toward an empty one. 
;; nice explanation of the powerset algorithm.
;;	http://www.ecst.csuchico.edu/~amk/foo/csci356/notes/ch1/solutions/recursionSol.html 
;; the ref'd article noted that for a given set S, we find the powerset by recursively removing an element 'rfs'
;; the powerset is equal to:
;; powerset(S - rfs) UNIONed with { {rfs} UNIONed with each resulting element of the powerset(S-rfs)}

(declare mps)

;; powerset method.
;; this is a first pass. basically, I am producing all the known sets and then filtering, 
;; determining which set has greatest value.
(defn powerset [items]

	(if (not-empty items)
		(union (mps (next items))	(map #(conj % (first items)) (mps (next items) )) )
		(list '())
	)
) ;; end, powerset


;; memoize
(def mps (memoize powerset))

(defn get-dolls [available-dolls weight-restriction]
	"obtain collection from available-dolls with greatest total value given weight-restriction"

		;; grab all paths, drop those paths whose weight is in excess of retricted. and determine max-of among the remaining.
		;; the path-summation-util mapping allows us to store the weight and value, associated set for each path.
		;; the call to retrieve associated set is the resultant set w greatest value
		(:associated-set	(max-of (filter #(< (:weight %) weight-restriction ) (map path-summation-util (powerset available-dolls) )) :value))
)

(defn -main 
	([]
		(println "Expected inputs: Collection (of available dolls) Weight (knapsack restriction)")
	)
	([available-containers weight-restriction]
		(println "placeholder called, pass in the map here.")
	)
)

