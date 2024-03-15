(defrule inicio
=>
(assert (hecho 1 2 3 1 4 2))
(assert (unico)))

(defrule salida
?b <- (hecho $?a)
=>
(printout t "Los valores repetidos son: " $?a crlf))
(retract ?b)
(assert (hecho)))

(defrule unique
?a <- (hecho $?q ?x $?w)
?b <- (unico $?m)
(not(unico $? ?x $?))
=>
(retract ?a)
(retract ?b)
(assert (hecho $?q $?w))
(assert (unico $?m ?x)))