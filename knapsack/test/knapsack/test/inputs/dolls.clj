(ns knapsack.test.inputs.dolls)

;; add list of inputs.
(def weight 400)
(def input [{:name "ben" :value 20 :weight 5} {:name "owen" :value 15 :weight 15} {:name "june" :value 10 :weight 8}])
(def output [ {:name "june" :value 10 :weight 8}  {:name "owen" :value 15 :weight 15}])

;; weight restriction on the master set.
(def master-weight-restriction 400)

;; this is the set of inputs for the master test. The outputs are known and def'd by the initial project.
(def master [
	{:name "luke" :weight 9 :value 150}
	{:name "anthony" :weight 13 :value 35}
	{:name "candice" :weight 153 :value 200}
	{:name "dorothy" :weight 50 :value 160}
	{:name "puppy" :weight   15 :value  60}
	{:name "thomas" :weight  68 :value  45}
	{:name "randal" :weight  27 :value  60}
	{:name "april" :weight   39 :value  40}
	{:name "nancy" :weight   23 :value  30}
	{:name "bonnie" :weight  52 :value  10}
	{:name "marc" :weight    11 :value  70}
	{:name "kate" :weight    32 :value  30}
	{:name "tbone" :weight   24 :value  15}
	{:name "tranny" :weight  48 :value  10}
	{:name "uma" :weight     73 :value  40}
	{:name "grumpkin" :weight 42 :value 70}
	{:name "dusty" :weight 43 :value 75}
	{:name "grumpy" :weight 22 :value 80}
	{:name "eddie" :weight 7 :value 20}
	{:name "tory" :weight 18 :value 12}
	{:name "sally" :weight 4 :value 50}
	{:name "babe" :weight 30 :value 10}
])

