(ns knapsack.util.file
	(:use [clojure.string :only [split]])
	(:gen-class)
)


;; 3 entries per line in a valid input file.
(def NUM_ENTRIES_PER_LINE 3)

;; a line is valid iff it it contains 3 entries
(defn is-line-valid? [entry] 
	"a line is valid iff it has 3 entries (name, weight, value)"
	
	(do
		(and (not-empty entry) (= NUM_ENTRIES_PER_LINE (count (split entry #"[\W.]+"))))
	)
)

(defn item-input-to-map [entry] 
	"convert (name, weight, value) line entry to map"

	;; entry is of form ' "itemName" weight value ' where itemName is a string, value/weight are integers
	(if (not-empty entry)
		(zipmap  [:name :weight :value] (cons (first (split entry #"[\W.]+")) (map #(Integer/parseInt %) (re-seq #"[\d.]+" entry))))
	)
)

(defn strip-empty-lines [items] 
	"Remove all empty lines from items"

	;; a line must have some text to be considered acceptable for analysis
	(filter #(> (count (first (split % #"\s"))) 0 ) items)
)

