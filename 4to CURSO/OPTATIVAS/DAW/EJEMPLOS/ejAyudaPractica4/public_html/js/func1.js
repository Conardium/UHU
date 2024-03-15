
/* Dibujando el menú */
/* El menú está almacenado en un array, pero podría ser también un JSON*/
/* Se recorre la estructura creando una cadena con el contenido HTML */

let menu = [{name: "Home", url: "#", options: []},
            {name: "Publica", url: "#", options: [{name: "Publica1", url: "#", options: []},
                                        {name: "Publica2", url: "#", options: []},
                                        {name: "Publica3", url: "#", options: ["Publica31", "Publica32", "Publica33"]}]},
            {name: "Consulta", url: "#", options: []},
            {name: "Alta", url: "#", options: []}
];

let menuHTML = '<ul class="menu">';

for (let i in menu) {
    menuHTML += "<li><a href='" + menu[i].url + "'>" + menu[i].name + "</a>";
    for (let j in menu[i].options) {
        if (j == 0) {
            menuHTML += "<ul>";
        }
        menuHTML += "<li><a href='" + menu[i].options[j].url + "'>" + menu[i].options[j].name + "</a>";
        for (let k in menu[i].options[j].options) {
            if (k == 0) {
                menuHTML += "<ul>";
            }
            menuHTML += "<li><a>" + menu[i].options[j].options[k] + "</a></li>";
            if (k == menu[i].options[j].options.length - 1) {
                menuHTML += "</ul>";
            }
        }
        menuHTML += "</li>";
        if (j == menu[i].options.length - 1) {
            menuHTML += "</ul>";
        }

    }
    menuHTML += "</li>";
}
menuHTML += "</ul>";

let nav = document.getElementsByTagName("nav")[0];
nav.innerHTML = menuHTML;


/* Completando el Select con las opciones (options) */
/* La parte comentada del código es otra forma de creación dinámica de contenido */

let prov = document.getElementById("prov");

window.onload = function () {
    let provincias = new Array("Álava", "Albacete", "Alicante", "Almería", "Ávila", "Badajoz", "Baleares", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Castellón", "Ciudad Real", "Córdoba", "Coruña", "Cuenca", "Girona", "Granada", "Guadalajara", "Guipuzcoa", "Huelva", "Huesca", "Jaén", "León", "Lleida", "Rioja", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense", "Asturias", "Palencia", "Las Palmas", "Pontevedra", "Salamanca", "Tenerife", "Cantabria", "Segovia", "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza", "Ceuta", "Melilla");
    for (let p in provincias) {
        prov.options[p] = new Option(provincias[p], p);
//        let op = document.createElement("option");
//        let opText = document.createTextNode(provincias[p]);
//        let opPar = document.createAttribute("value");       // Create a "value" attribute
//        opPar.value = p;                           // Set the value of the class attribute
//        op.setAttributeNode(opPar);
//        op.appendChild(opText);
//        prov.appendChild(op);
    }

};


/* Asignar provincia al introducir el Código Postal */

let cp = document.getElementById("cp");
cp.onchange = function () {
    let opciones = document.getElementsByTagName("option");
    
    let cpProv = parseInt(cp.value.substr(0, 2));

    for (let op of opciones) {
        if(op.hasAttribute("selected")) {
            op.removeAttribute("selected");
        }
        if (op.value == cpProv - 1) {
            op.setAttribute("selected", "");
        }
    }
};

