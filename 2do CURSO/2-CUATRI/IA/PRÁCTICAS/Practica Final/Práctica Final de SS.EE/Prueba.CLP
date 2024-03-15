(deffacts sintomas
			(sint Nauseas)
	)
	(deftemplate Especialista
			(field esp)
			(multifield medcm)
	)
	(deftemplate Medicamento
			(field medcm)
			(multifield enf)
	)
	(deftemplate Enfermedad
			(field enf)
			(multifield sint)
	)
	(defrule buscarEnfermedad
			(sint $? ?s $?)
			(Enfermedad
					(enf ?e)
					(sint $? ?s $?))
			(not (enf $? ?e $?))
			?enferm <- (enf $?en)
			=>
			(retract ?enferm)
			(assert (enf ?en ?e))
			(printout t "Puedes tener " ?e " debido al sintoma " ?s crlf)
	)
	(defrule buscarMedicamentoYEspecialista
			(enf $? ?e $?)
			(Medicamento
					(medcm ?m)
					(enf $? ?e $?))
			(Especialista
					(esp ?es)
					(medcm $? ?m $?))

			(not (medcm $? ?m $?))
			?medic <- (medcm $?md)
			=>
			(retract ?medic)
			(assert (medcm ?md ?m))
			(printout t "Si tienes " ?e " podrias ir al " ?es " y este te recetara " ?m crlf crlf)
	)