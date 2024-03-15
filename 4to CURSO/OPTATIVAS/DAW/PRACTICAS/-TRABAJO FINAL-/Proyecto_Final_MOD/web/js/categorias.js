
function checkCategoria(){
    var pNombre = document.getElementById("pNombre");
    pNombre.innerHTML = "";
    
    var ok = "true";
    var formCategorias = document.getElementById("formCategorias");
    
    if (formCategorias.nombre.value === "") {
        ok = false;
        pNombre.innerHTML = "El nombre de la categoría no puede ser vacío";
    }
    ;
    
    return ok;
}

function validarBorrado(){
    return(confirm("¿Seguro que deseas borrar?"));
}