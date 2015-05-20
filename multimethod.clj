(ns example)

;; Define "area" functionality to dispatch based on the :Shape key (implies "area" accepts a map as an argument)
(defmulti area :Shape)

;; Define a function that makes a map where key :Shape -> :Rect
(defn rect [wd ht] {:Shape :Rect :wd wd :ht ht})

;; Define a function that makes a map where key :Shape -> :Circle
(defn circle [radius] {:Shape :Circle :radius radius})

;; Extend the "area" functionality to support :Rect
(defmethod area :Rect [r]
    (* (:wd r) (:ht r)))

;; Extend the "area" functionality to support :Circle
(defmethod area :Circle [c]
    (* (. Math PI) (:radius c) (:radius c)))

(def r1 (rect 10 20))
(def c1 (circle 12))

(area r1)
(area c1)

; Some other (futute) file wants to extend supported shapes to include Squares:

(defmethod area :Square [s] (* (:side s) (:side s)))

(area {:Shape :Square :side 10})
