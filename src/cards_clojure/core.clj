(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hearts :diamonds])
(def ranks (range 1 14))

(defn create-deck []
  (set
    (for [suit suits
          rank ranks]
     {:suit suit :rank rank})))

(defn create-hands [deck]
  (set
      (for [c1 deck
            c2 (disj deck c1)
            c3 (disj deck c1 c2)
            c4 (disj deck c1 c2 c3)]
        #{c1 c2 c3 c4})))

(defn flush? [hand]
  (let [suits (set(map :suit hand))]
    (= 1 (count suits))))

(defn straight? [hand]
  (and (= 4 (count (set (map :rank hand))))
       (= 3 (- (apply max (map :rank hand))
               (apply min (map :rank hand))))))

(defn fourofakind? [hand]
  (let [ranks (set(map :rank hand))]
    (= 1 (count ranks))))

(defn straightflush? [hand]
  (and (straight? hand)
       (flush? hand)))
       

;(defn threeofakind? [hand]
;  (let))
       
       
  
  

(defn -main []
  (let [deck (create-deck)
        hands (create-hands deck)
        flushes (filter flush? hands)
        straights (filter straight? hands)
        fourofakind (filter fourofakind? hands)
        straightflush (filter straightflush? hands)]
    
    (count straightflush)))
