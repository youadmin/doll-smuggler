(ns knapsack.util.formatter
	(:gen-class)
)


(defn output-result [items]
	;; print the resultant set.

	(if (not-empty items)
		(do 
			(println "The following dolls should be added to the backback: ")
			(println (str "Total weight of collection: " (.toString (reduce + (map :weight items) )) ))
			(println (str "Total value of collection: " (.toString (reduce + (map :value items) )) ))
			(doseq [item items] (println (str "Item name: " (:name item) ", weight: " (:weight item) ", value: " (:value item) )))
		)
		(println "Calculation complete however the result set appears empty. Please check your inputs.")
	)

)

