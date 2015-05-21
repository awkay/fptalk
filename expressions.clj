; In infix notation, overriding precedence gets ugly:

; 5 + (3 + ((11 + 4 + 5) * 20))  / 21

; It is "easy" in the sense that we are "used to it", but complex in that we have to follow rules of algebra to read it

; nested expressions in clojure are similarly painful by default (though simpler in terms of precedence always being explicit)
(+ 5
   (/ (+ 3
      (* 20
         (+ 11 4 5)
      )
   ) 21)
)

; but since code is data, it is possible to "transform" the code using a macro (->) that "threads" expressions,
; resulting something simpler than both:
(-> (+ 11 4 5)  ; Sum up 11, 4, and 5
    (* 20)      ; multiply "that" by 20
    (+ 3)       ; add 3 to "that"
    (/ 21)      ; divide "that" by 21
    (+ 5))      ; and add 5 to it


; Macros are simple to write because (since code is data) they are simply data transforms, and all of the pre-existing
; data manipulation functions work!

; the above macro (->) is part of the std lib, and is:
;(defmacro -> [x & forms]
;  (loop [x x, forms forms]
;    (if forms
;      (let [form (first forms)
;            threaded (if (seq? form)
;                       (with-meta `(~(first form) ~x ~@(next form)) (meta form))
;                       (list form x))]
;        (recur threaded (next forms)))
;      x)))

; So, eval of the prior expression is rewritten to the "harder to read" code:
;            (+ 11 4 5)
;                \/
;         (*            20)
;                \/
;      (+                   3)
;                \/
;   (/                         21)
;                \/
; (+                               5)
