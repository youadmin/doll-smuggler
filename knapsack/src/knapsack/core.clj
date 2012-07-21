(ns knapsack.core
	(:gen-class)
)


(defn get-dolls [available-dolls weight-restriction]
	"obtain collection from available-dolls with greatest total value given weight-restriction"

	;; getting started, just return a sampling
	(rest available-dolls)
	
)


(defn -main 
	([]
		(println "Expected inputs: Collection (of available dolls) Weight (knapsack restriction)")
	)
	([available-containers weight-restriction]
		(println "placeholder called, pass in the map here.")
	)
)

