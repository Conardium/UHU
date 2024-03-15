
var xhr;

function init_ajax() {
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function checkForm() {
    var pNombre = document.getElementById("pNombre");
    pNombre.innerHTML = "";
    var pCorreo = document.getElementById("pCorreo");
    pCorreo.innerHTML = "";
    var pClave = document.getElementById("pClave");
    pClave.innerHTML = "";
    var pRepeClave = document.getElementById("pRepeClave");
    pRepeClave.innerHTML = "";
    var pCodigoPostal = document.getElementById("pCodigoPostal");
    pCodigoPostal.innerHTML = "";
    var pTelefono = document.getElementById("pTelefono");
    pTelefono.innerHTML = "";

    var ok = "true";
    var formRegister = document.getElementById("formRegister");

    if (formRegister.usuario.value === "") {
        ok = false;
        pNombre.innerHTML = "El usuario no puede ser vacío";
    }
    ;

    if (formRegister.correo.value === "") {
        ok = false;
        pCorreo.innerHTML = "El correo no puede ser vacío";
    }
    ;

    if (formRegister.passw.value === "") {
        ok = false;
        pClave.innerHTML = "Ingresa una contraseña";
    }
    ;

    if (formRegister.passw.value !== formRegister.passw2.value) {
        ok = false;
        pRepeClave.innerHTML = "Ambas contraseñas deben ser iguales";
    }
    ;

    if (formRegister.cp.value === "") {
        ok = false;
        pCodigoPostal.innerHTML = "Ingresa un código postal";
    }
    ;

    if (formRegister.telf.value === "") {
        ok = false;
        pTelefono.innerHTML = "Ingresa un número de teléfono";
    }
    ;
    return ok;
}

function cerrarMensaje() {
    document.getElementById("msgErrores").style.display = "none";
    document.getElementById("msgCorrectos").style.display = "none";
}

function cerrarMensajeArticulo() {
    document.getElementById("msgCorrectos").style.display = "none";
}

function checkPublicar() {
    var pNombreArt = document.getElementById("pNombreArt");
    pNombreArt.innerHTML = "";
    var pPrecio = document.getElementById("pPrecio");
    pPrecio.innerHTML = "";
    var pFecha = document.getElementById("pFecha");
    pFecha.innerHTML = "";

    var ok = "true";
    var formPublic = document.getElementById("formPublic");

    if (formPublic.nombre.value === "") {
        ok = false;
        pNombreArt.innerHTML = "Falta el nombre del artículo";
    }
    ;

    if (formPublic.precio.value <= 0) {
        ok = false;
        pPrecio.innerHTML = "Indique un precio válido";
    }
    ;

    if (formPublic.precio.value === "") {
        ok = false;
        pPrecio.innerHTML = "Hace falta poner un precio al articulo";
    }
    ;

    if (formPublic.fecha.value < 1000) {
        ok = false;
        pFecha.innerHTML = "Indica una fecha válida";
    }
    ;

    if (formPublic.fecha.value === "") {
        ok = false;
        pFecha.innerHTML = "Indica una fecha";
    }
    ;

    return ok;
}


function borrarFiltro(){
    let nombreBuscar = document.getElementById("fnombre");
    let generoBuscar = document.getElementById("fgenero");
    let precioBuscar = document.getElementById("fprecio");
    
    nombreBuscar.value="";
    generoBuscar.value="";
    precioBuscar.value="";
}

//Para el filtrado de la lista de articulos
function filtrar() {

    init_ajax();
    
    var url = "filtro";
    xhr.open("POST", url, true);
    xhr.onreadystatechange = articulosFiltrados;

    var nombreBuscar = document.getElementById("fnombre");
    var generoBuscar = document.getElementById("fgenero");
    var precioBuscar = document.getElementById("fprecio");

    var datos = "nombreBuscar=" + encodeURIComponent(nombreBuscar.value) +
            "&generoBuscar=" + encodeURIComponent(generoBuscar.value) +
            "&precioBuscar=" + encodeURIComponent(precioBuscar.value);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    
    xhr.send(datos);
}

function articulosFiltrados() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            document.getElementById("respuesta").innerHTML = xhr.responseText;
        }
    }
}