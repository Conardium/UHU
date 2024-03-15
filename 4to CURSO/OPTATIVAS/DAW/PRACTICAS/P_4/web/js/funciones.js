

/*======= Creacion del menu dinámico =========*/

let menu = [{name: "Inicio", url: "practica2template.html", options: [], title: "Vuelve al inicio"},
    {name: "Categorias", url: "categorias.html", options: [], title: "Redirige a las categorias"},
    {name: "Formulario", url: "formulario.html", options: [], title: "Redirige al formulario"},
    {name: "Articulos", url: "articulos.html", options: [], title: "Redirige a los articulos"}
];

let menuHTML = '<ul>'; /*Inicio del list*/

for (let i in menu) {
    menuHTML += "<li><a href='" + menu[i].url + "' title='" + menu[i].title + "'>" + menu[i].name + "</a>";
    menuHTML += "</li>";
}
menuHTML += "</ul>";

let nav = document.getElementById("navegacion");
nav.innerHTML = menuHTML;


/*Funcion para cambiar el color de una fila de la tabla de articulos al hacer clic en la celda de "precio"
 * 
 * La primera vez el color de fondo es ROJO y letra BLANCA
 * La segunda vez el color de fondo es AZUL y letra AMARILLA
 * Y a la tercera vez se tiene que reestablecer los colores predeterminados
 * */

let filas = document.getElementsByTagName("tr");

function cambiarColorFila(id){
    
    if(id%2 == 0) //Si la fila es par
    {
        if (filas[id].getAttribute("class") == "porDefectoPar") {
            filas[id].classList.remove("porDefectoPar");
            filas[id].classList.add("filaRojo");
        } 
        else if (filas[id].getAttribute("class") == "filaRojo") {
            filas[id].classList.remove("filaRojo");
            filas[id].classList.add("filaAzul");
        } 
        else if (filas[id].getAttribute("class") == "filaAzul") {
            filas[id].classList.remove("filaAzul");
            filas[id].classList.add("porDefectoPar");
        }
    }
    else
    {
        if (filas[id].getAttribute("class") == "porDefectoImpar") {
            filas[id].classList.remove("porDefectoImpar");
            filas[id].classList.add("filaRojo");
        } 
        else if (filas[id].getAttribute("class") == "filaRojo") {
            filas[id].classList.remove("filaRojo");
            filas[id].classList.add("filaAzul");
        } 
        else if (filas[id].getAttribute("class") == "filaAzul") {
            filas[id].classList.remove("filaAzul");
            filas[id].classList.add("porDefectoImpar");
        }
    }
    
}


// ======= Crear de forma dinamica el resto de opciones de las provincias =========
let prov = document.getElementById("prov");

window.onload = function () {
    let provincias = new Array("Álava", "Albacete", "Alicante", "Almería", "Ávila", "Badajoz", "Baleares", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Castellón", "Ciudad Real", "Córdoba", "Coruña", "Cuenca", "Girona", "Granada", "Guadalajara", "Guipuzcoa", "Huelva", "Huesca", "Jaén", "León", "Lleida", "Rioja", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Asturias", "Palencia", "Las Palmas", "Pontevedra", "Salamanca", "Tenerife", "Cantabria", "Segovia", "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza", "Ceuta", "Melilla");
    for (let p in provincias) {
        prov.options[p] = new Option(provincias[p], p);
    }
};

//============== Asignar provinicia al introducir el codigo postal ==============
let cp = document.getElementById("cp");
cp.onchange = function () {
    let opciones = document.getElementsByTagName("option");
    let cpProv = parseInt(cp.value.substr(0, 2));

    for (let op of opciones) {
        if (op.hasAttribute("selected")) {
            op.removeAttribute("selected");
        }
        if (op.value == cpProv - 1) {
            op.setAttribute("selected", "");
        }
    }
};


function validar() {
    var spanCorreo = document.getElementById("spanCorreo");
    spanCorreo.innerHTML = "";
    var spanClave = document.getElementById("spanClave");
    spanClave.innerHTML = "";
    var spanRepClave = document.getElementById("spanRepClave");
    spanRepClave.innerHTML = "";
    var spanNombre = document.getElementById("spanNombre");
    spanNombre.innerHTML = "";

    var msgGeneral = document.getElementById("msgGeneral");
    msgGeneral.innerHTML = "";

    let p = document.createElement("p");
    let tp = document.createTextNode("Inserte correctamente la siguiente información:");
    p.appendChild(tp);
    let ul = document.createElement("ul");
    var ok = "true";

    var f = document.getElementById("f");
    if (f.correo.value === "") {
        ok = false;
        spanCorreo.innerHTML = "Correo requerido: Inserta Correo";
            let li1 = document.createElement("li");
            let tl1 = document.createTextNode("Correo");
            li1.appendChild(tl1);
            ul.appendChild(li1);
    };
    if (f.clave.value === "") {
        ok = false;
        spanClave.innerHTML = "Clave Requerida: Inserta Clave";
            let li2 = document.createElement("li");
            let tl2 = document.createTextNode("Contraseña");
            li2.appendChild(tl2);
            ul.appendChild(li2);
    };
    if (f.Repclave.value === "") {
        ok = false;
        spanRepClave.innerHTML = "Repetir Clave requerida: Inserta la Clave repetida"
            let li3 = document.createElement("li");
            let tl3 = document.createTextNode("Repetir Contraseña");
            li3.appendChild(tl3);
            ul.appendChild(li3);
    };
    if (f.clave.value !== f.Repclave.value) {
        ok = false;
        spanRepClave.innerHTML = "Claves distintas: Ambas claves deben coincidir"
            let li4 = document.createElement("li");
            let tl4 = document.createTextNode("Repetir Contraseña y/o Contraseña");
            li4.appendChild(tl4);
            ul.appendChild(li4);
    };
    if(f.name.value === ""){
        ok = false;
        spanNombre.innerHTML = "Nombre Requerido: Inserta Nombre";
            let li5 = document.createElement("li");
            let tl5 = document.createTextNode("Nombre");
            li5.appendChild(tl5);
            ul.appendChild(li5);
    };
    
    if(f.clave.value.length < 6){
        ok = false;
        spanClave.innerHTML = "La contraseña debe de ser como minimo de 6 caracteres";
            let li6 = document.createElement("li");
            let tl6 = document.createTextNode("Longitud de la Contraseña");
            li6.appendChild(tl6);
            ul.appendChild(li6);
    };
    
    if (!ok) {
        let msg = document.getElementById("msgGeneral");
        msg.appendChild(p);
        msg.appendChild(ul);
        msgGeneral.style.display = "block";
    };
    
    return ok;
}

