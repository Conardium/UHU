
var xhr;

function init_ajax(){
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }   
    
}

function validarIdDB() {

    init_ajax();
    
    var url = "idValido";
    xhr.open("POST", url, true);
    xhr.onreadystatechange = idValido;

    var id = document.getElementById("id");

    var datos = "id=" + encodeURIComponent(id.value);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(datos);
}

function idValido() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            document.getElementById("idvalido").innerHTML = xhr.responseText; //Sustituye en home la etiqueta idvalido por el xhr.responseText
        }
    }

}

//Para el filtrado de la lista de articulos
function filtrar() {

    init_ajax();
    
    var url = "filtro";
    xhr.open("POST", url, true);
    xhr.onreadystatechange = articulosFiltrados;
    
    console.log("xhr es: " + xhr.onreadystatechange);

    var nombre = document.getElementById("name");
    var pmenor = document.getElementById("pmenor");
    var pmayor = document.getElementById("pmayor");

    var datos = "nombre=" + encodeURIComponent(nombre.value) +
            "&pmenor=" + encodeURIComponent(pmenor.value) +
            "&pmayor=" + encodeURIComponent(pmayor.value);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    console.log("datos vale: " + datos);
    
    xhr.send(datos);

}

function articulosFiltrados() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            document.getElementById("respuesta").innerHTML = xhr.responseText;
        }
    }

}