

/* Validar campos del formulario y mostrar mensajes de error */

function validar() {

	var spanName= document.getElementById("spanName");
	spanName.innerHTML="";
	var spanClave= document.getElementById("spanClave");
	spanClave.innerHTML="";

        var msgForm = document.getElementById("msgForm");
        msgForm.innerHTML="";
        
        let p = document.createElement("p");
        let tp = document.createTextNode("Inserte correctamente la siguiente información:");
        p.appendChild(tp);
        let ul = document.createElement("ul");
	var ok="true";

	var f = document.getElementById("f");
	if (f.name.value==="") {
		ok=false;
		spanName.innerHTML="Nombre Requerido: Inserta Nombre";
                let li1 = document.createElement("li");
                let tl1 = document.createTextNode("Nombre");
                li1.appendChild(tl1);
                ul.appendChild(li1);

	};
	if (f.clave.value==="") {
		ok=false;
		spanClave.innerHTML="Clave Requerida: Inserta Clave";
                let li2 = document.createElement("li");
                let tl2 = document.createTextNode("Clave"); 
                li2.appendChild(tl2);
                ul.appendChild(li2);                
	};
        
	// Resto de los campos

        // Si hay errores los añadimos al elemento msgForm y lo mostramos
	if (!ok) {
            let msg = document.getElementById("msgForm");
            msg.appendChild(p);
            msg.appendChild(ul);
            msgForm.style.display="block";
	};
        
        // Devolvemos true o, si hay errores, false
	return ok;
}

