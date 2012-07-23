(ns knapsack.core
	(:use [knapsack.util.file :only [is-line-valid? item-input-to-map strip-empty-lines]])
	(:use [knapsack.util.formatter :only [output-result]])
	(:use [clojure.set])
	(:use [clojure.java.io :only [reader]])
	(:use [clojure.string :only [split]])
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
;; nice explanation of the powerset algorithm.
;;	http://www.ecst.csuchico.edu/~amk/foo/csci356/notes/ch1/solutions/recursionSol.html 
;; From the ref'd article, it is noted that for a given set S, we find the powerset by recursively removing an element 'rfs'
;; and calculating:
;; powerset(S - rfs) UNIONed with { {rfs} UNIONed with each resulting element of the powerset(S-rfs)}

;; for memoization
(declare mps)

(defn powerset [items]
	"build the powerset for items"

	(if (not-empty items)
		(union (mps (next items))	(map #(conj % (first items)) (mps (next items) )) )
		(list '())
	)
) ;; end, powerset


;; cache the resultant sets.
(def mps (memoize powerset))

(defn get-dolls [available-dolls weight-restriction]
	"obtain collection from available-dolls with greatest total value given weight-restriction"

		;; grab all paths, drop those paths whose weight is in excess of retricted. and determine max-of among the remaining.
		(if-let [all-sets (powerset available-dolls)]

			;; map each set to a new hash with a weight and value summary, 
				;; remove all paths that exceeded the weight restriction
			(let [mapped (map path-summation-util all-sets) made-weight (filter #(<= (:weight %) weight-restriction ) mapped ) ]  

				;; find best knapsack collection by :value total and return the set
				(:associated-set	(max-of made-weight :value))	

			)
			(list '())
		)
)

(defn -main 
	([]
		(println (str "Expected input is a file whose contents contain a line for each knapsack item and a maximum content weight for the sack. \n" 
				"Sample line entry: Sally 105 22 " "\n represents item with name: Sally, weight: 105 and value: 22"))
	)
	([file-path maximum-sack-weight]
		(do
			(println (str "Processing input file: " file-path "\n for maximum sack weight: " maximum-sack-weight))

			(with-open [rdr (reader file-path)]

				(let [raw-lines (line-seq rdr) mw (Integer/parseInt maximum-sack-weight) lines (strip-empty-lines raw-lines)]
					(if (and (> mw 0) (every? is-line-valid? lines))
						(output-result (get-dolls (map item-input-to-map lines) mw)	)
						(println (str "The input file: " file-path  " or the maximum weight " maximum-sack-weight 
										" does not appear valid. \n" "Each line must include Name Weight Value for the file to be valid."))
					)
				)
			) 
		) 

	)
)

