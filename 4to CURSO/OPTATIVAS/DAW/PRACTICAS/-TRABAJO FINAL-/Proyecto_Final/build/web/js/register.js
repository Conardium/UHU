
var xhr;

function init_ajax() {
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function comprobarCorreoValido() {

    init_ajax();
    
    var url = "validacionCorreo";
    xhr.open("POST", url, true);
    xhr.onreadystatechange = correoValidoFunction;

    var correoBuscar = document.getElementById("correo");

    var correoValido = "correoBuscar=" + encodeURIComponent(correoBuscar.value);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    
    xhr.send(correoValido);
}

function correoValidoFunction() {
    if (xhr.readyState === 4) {
        if (xhr.status === 200) {
            document.getElementById("pCorreo").innerHTML = xhr.responseText;
        }
    }
}