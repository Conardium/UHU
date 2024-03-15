
function ocultarBoton(){
    let botonComentario = document.getElementById("boton-agregar-comentario");
    let formPublicarComentario = document.getElementById("div-form-comentario");
    
    botonComentario.style.display= "none";
    formPublicarComentario.style.display="block";
}

function ocultarForm(){
    let botonComentario = document.getElementById("boton-agregar-comentario");
    let formPublicarComentario = document.getElementById("div-form-comentario");
    
    botonComentario.style.display= "block";
    formPublicarComentario.style.display="none";
}

