<%-- 
    Document   : publicarJuegos
    Created on : 21-dic-2022, 15:27:28
    Author     : ismae
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito Sans">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Press Start 2P">

        <link href="/PFdaw/css/estilo.css" rel="stylesheet" type="text/css" />
        <link href="/PFdaw/css/publicarArticulo.css" rel="stylesheet" type="text/css"/>
        <link href="/PFdaw/css/seleccionados/publicarJuegoSelec.css" rel="stylesheet" type="text/css"/>
    </head>

    <body class="fondo">
        <div id="base">
            <header>
                <div>
                    <img src="/PFdaw/img/icono_titulo.png" id="icono_titulo"/>
                    GameSpace
                    <c:if test="${sessionScope.user!=null}"> 
                        <p>Bienvenido ${sessionScope.user}</p>
                    </c:if>
                </div>
            </header>

            <img src="/PFdaw/img/portada.jpg" id="portada"/>

            <div id="msgErrores">
                <c:if test="${requestScope.msgDenegado!=null}"> 
                    <p>${requestScope.msgDenegado}</p> 
                    <div id = "equis">
                        <input type="button" onclick="cerrarMensaje()" value="X"/>
                    </div>
                </c:if>
            </div>

            <div id="msgCorrectos">
                <c:if test="${requestScope.msgOK!=null}"> 
                    <p>${requestScope.msgOK}</p> 
                    <div id = "equisOK">
                        <input type="button" onclick="cerrarMensaje()" value="X"/>
                    </div>
                </c:if>
            </div>

            <nav>
                <jsp:include page="menu.jsp"></jsp:include>
            </nav>

            <section class="contenedor-publicar">
                <h2>Publicar un juego</h2>
                <form id="formPublic" method="POST" onsubmit="return checkPublicar()" action="crearJuego" enctype="multipart/form-data">
                    <c:if test="${requestScope.msgERRORpublicar!=null}"><p class="errorPublicar">${requestScope.msgERRORpublicar}</p></c:if>

                    <div class="contenedor-articulo">
                        <input id="nombre" type="text" name="nombre">
                        <label>Nombre:</label>
                        <p id="pNombreArt" class="errorP"></p>
                    </div>
                    <div class="contenedor-articulo">
                        <input id="precio" type="number" name="precio">
                        <label>Precio:</label>
                        <p id="pPrecio" class="errorP"></p>
                    </div>

                    <div class="contenedor-select">
                        <label for="">Género: </label>
                        <select name="generoArticulo" id="generoArticulo">
                            <option value="Acción">Acción</option>
                            <option value="Shooter">Shooter</option>
                            <option value="Aventura">Aventura</option>
                            <option value="Estrategia">Estrategia</option>
                            <option value="Simulación">Simulación</option>
                            <option value="Deporte">Deporte</option>
                            <option value="Carreras">Carreras</option>
                            <option value="RPG">RPG</option>
                            <option value="Muscical">Musical</option>
                            <option value="Puzzles">Puzzles</option>
                            <option value="Sandbox">Sandbox</option>
                        </select>
                    </div>
                    
                    <div class="contenedor-articulo">
                        <input id="fecha" type="number" name="fecha" placeholder="Formato: aaaa" pattern="[1-9]{1}[0-9]{3}">
                        <label>Año de adquisición:</label>
                        <p id="pFecha" class="errorP"></p>
                    </div>
                    
                    <div class="contenedor-articulo">
                        <input type="file" name="imgArticulo" value="" />
                        <label>Imagen del juego: </label>
                    </div>
                    
                    <div class="contenedor-textarea">
                        <label>Descripción: </label>
                        <textarea wrap="true" id="descripcion" name="descripcion" placeholder="Máximo 255 carácteres..."></textarea>
                    </div>

                    <div>
                        <input id="enviar" type="submit" value="Publicar" class="boton-enviar">
                    </div>
                </form>
            </section>

            <footer>
                <p> &copy; Ismael Da Palma Fernández, DAW 2022-23 </p>
            </footer>
        </div>
    </body>
    <script src="/PFdaw/js/funciones.js" type="text/javascript"></script>
</html>
