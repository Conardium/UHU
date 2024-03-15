(deffacts sistema

	(Especialista
			(esp Medicogeneral)
			(medcm Vacuna Pastilla)
	)
	(Especialista
			(esp Nutricionista)
			(medcm Vitamina)
	)
	(Especialista
			(esp Endocrinologo)
			(medcm Vacuna)
	)
	(Especialista
			(esp Otorrino)
			(medcm Jarabe Contrex)
	)

	(Medicamento
			(medcm Pastilla)
			(enf Rubeola Hepatitis Tuberculosis)
	)
	(Medicamento
			(medcm Vacuna)
			(enf Gripe Rubeola Malaria Hepatitis)
	)
	(Medicamento 
			(medcm Vitamina)
			(enf Anemia)
	)
	(Medicamento
			(medcm Contrex)
			(enf Gripe)	
	)
	(Medicamento
			(medcm Jarabe)
			(enf Gripe)
	)

	(Enfermedad
			(enf Anemia)
			(sint Cansancio Nauseas Apatia)
	)
	(Enfermedad
			(enf Tuberculosis)
			(sint Tos Cansancio Fiebre Escalofrios)
	)
	(Enfermedad
			(enf Hepatitis)
			(sint Diarrea Nauseas Ictericia)
	)
	(Enfermedad
			(enf Malaris)
			(sint Diarrea Fiebre Ictericia Escalofrios)
	)
	(Enfermedad
			(enf Rubeola)
			(sint Fiebre Escalofrios Jaqueca Secredon)
	)
	(Enfermedad
			(enf Gripe)
			(sint Tos Cansancio Fiebre Dolor)
	)
)

(deffacts diagnosticoFinal
		(medcm)
		(enf)
)