(defrule inicio
=>
(assert (hecho 1 3 5 2 3 9))
(assert (suma 0)))

(defrule suma-int
(hecho $?)
?x <- (hecho ?a $?b)
?s <- (suma ?total)
=>
(retract ?x)
(retract ?s)
(assert (suma (+ ?total ?a)))
(assert (hecho $?b)))

(defrule salida
(not(hecho $? ?a $?))
(suma ?total)
=>
(printout t "La suma es :" ?total crlf))

