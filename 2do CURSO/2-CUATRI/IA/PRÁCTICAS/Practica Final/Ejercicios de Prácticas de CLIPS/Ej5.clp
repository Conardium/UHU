(defrule inicio
=>
(assert (hecho 1 5 3 4 9)))

(defrule salida
(max ?a)
(min ?b)
=>
(printout t "Maximo(" ?a ") menos minimo(" ?b ") es : " (- ?a ?b) crlf))

(defrule maximo
(hecho $? ?x $?)
(not (hecho $? ?y&:(> ?y ?x) $?))
=>
(assert (max ?x)))

(defrule minimo
(hecho $? ?x $?)
(not (hecho $? ?y&:(< ?y ?x) $?))
=>
(assert (min ?x)))


