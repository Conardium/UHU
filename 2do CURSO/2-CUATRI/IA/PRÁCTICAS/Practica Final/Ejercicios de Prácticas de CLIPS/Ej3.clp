(defrule inicio
=>
(assert (hecho1 1 2 3 4 5 6))
(assert (hecho2 2 4 7 5))
(assert (hecho3)))

(defrule inter
?a <- (hecho1 $?q ?x $?w)
?b <- (hecho3 $?m)
(hecho2 $? ?x $?)
=>
(retract ?a)
(retract ?b)
(assert (hecho1 $?q $?w))
(assert (hecho3 $?m ?x)))

(defrule salida
(hecho3 $?)
?b <- (hecho3 $?a)
=>
(printout t "La interseccion es: " $?a crlf))
(retract ?b))