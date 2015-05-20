(def someMap { :k 1 :k2 2 :k3 3} )
(def someList [1 2 3 4 5] )
(def someSet #{1 3 4 5 6})

(def listOfMaps [
                 { :market "West" :v 2 }
                 { :market "East" :v 3 }
                 { :market "West" :v 4 }
                ])

; higher-order functions work well with them
(map inc someList)
(map inc someSet)
(map inc (vals someMap))

(group-by odd? someList)
(group-by :market listOfMaps)
(group-by odd? someSet)

(partition 2 someList)
(partition 2 someSet)
(partition-by :market listOfMaps)

; "modification" of content
(def m2 (assoc someMap :k4 6))
(def l2 (cons 9 someList))

; structural sharing, not mutation
someList
l2

someMap
m2


