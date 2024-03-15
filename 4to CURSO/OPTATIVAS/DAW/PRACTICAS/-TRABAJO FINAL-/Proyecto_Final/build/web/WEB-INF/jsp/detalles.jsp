
<%-- 
    Document   : home
    Created on : 6-ene-2023, 17:21:36
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
        <link href="/PFdaw/css/detalles.css" rel="stylesheet" type="text/css" />
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

                <section class="section-art">

                    <div class="div-img">
                        <img src="/PFdaw/img/${requestScope["articulo"].nombre}${requestScope["articulo"].añoAdquisicion}.jpg" alt="imagen-artículo"/>
                </div>

                <div class="div-datos-articulo">
                    <ul>
                        <li><h1>${requestScope["articulo"].nombre}</h1></li>
                        <li><button class="boton-genero">${requestScope["articulo"].genero}</button></li>
                        <li><h4>${requestScope["articulo"].precio}€</h4></li>    
                        <li class="li-anio"> Año de adquisición: <label>${requestScope["articulo"].añoAdquisicion}</label></li>
                            <c:if test="${sessionScope.user!=null}">
                            <li><button class="boton-favoritos"><a href="addFavorito?id=${requestScope["articulo"].id}">FAVORITOS</a></button></li>
                            </c:if>
                    </ul>                       
                </div>

                <div class="div-datos-usuario">
                    <ul>
                        <li><h1>Datos del vendedor</h1></li>
                        <li>Nombre: <label>${requestScope["articulo"].usuario.nombre}</label></li>
                        <li>Email: <label>${requestScope["articulo"].usuario.email}</label></li>
                        <li>Tlf. de Contacto: <label>${requestScope["articulo"].usuario.telefono}</label></li>
                        <li>Código Postal: <label>${requestScope["articulo"].usuario.codpostal}</label></li>
                    </ul>
                </div>

                <div class="div-detalles">
                    <h1>Descripción del artículo:</h1>
                    <p>${requestScope["articulo"].detalles}</p>
                </div>


                <div class="div-comentarios">
                    <h3>Comentarios:</h3>
                    
                    <c:if test="${sessionScope.user!=null}">
                        <button id="boton-agregar-comentario" onclick="return ocultarBoton();">Añadir comentario</button>
                    </c:if>
                        
                    <div id="div-form-comentario" style="display: none;">

                        <form id="formComentar" class="div-publicar-comentario" method="POST" action="publicarComentario" onsubmit="return validarFormComentario()">
                            <h2>Añadir un comentario</h2>
                            <div>
                                <label>Privacidad: </label><br/>
                                <input type="radio" value="Publico" id="radio-publico" name="privaciad" checked>
                                <label for="radio-publico">Público</label>
                                <input type="radio" value="Personal" id="radio-personal" name="privaciad">
                                <label for="radio-personal">Personal</label>
                                <input type="radio" value="Vendedor" id="radio-vendedor" name="privaciad">
                                <label for="radio-vendedor">Para el vendedor</label>
                            </div>
                            <label id="descripcion">Descripción: </label>
                            <input id="id-articulo-comentario" type="text" name="id-articulo-comentario" value="${requestScope["articulo"].id}" style="display: none">
                            <div>
                                <textarea id="publicar-comentario-descripcion" name="publicar-comentario-descripcion" placeholder="Máximo de 255 carácteres..."></textarea>
                            </div>

                            <div>
                                <input id="publicar" type="submit" value="Publicar comentario">
                                <button id="cancelar" type="reset" onclick="return ocultarForm()">Cancelar</button>
                            </div>
                        </form>
                    </div>

                    <c:forEach var="coment" items="${requestScope.comentarios}">
                        <c:choose>
                            <c:when test="${coment.privacidad eq 'Publico'}">
                                <div class="div-comentarios-publicos">
                                    <h4>${coment.usuario.nombre}</h4>
                                    <p>${coment.texto}</p>
                                </div>
                            </c:when>

                            <c:when test="${coment.privacidad eq 'Personal' and coment.usuario.id == sessionScope.id}">
                                <div class="div-comentarios-personal">
                                    <h4>Para mi: ${coment.usuario.nombre}</h4>
                                    <p>${coment.texto}</p>
                                </div>
                            </c:when>

                            <c:when test="${coment.privacidad eq 'Vendedor' and (sessionScope.id == coment.articulo.usuario.id) or (coment.usuario.id == sessionScope.id)}">
                                <div class="div-comentarios-vendedor">
                                    <h4>Para el vendedor, de ${coment.usuario.nombre}</h4>
                                    <p>${coment.texto}</p>
                                </div>
                            </c:when>

                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>                           
                    </c:forEach>
                </div>


            </section>

            <footer>
                <p> &copy; Ismael Da Palma Fernández, DAW 2022-23 </p>
            </footer>
        </div>
    </body>
    <script src="/PFdaw/js/detalles.js" type="text/javascript"></script>
    <script src="/PFdaw/js/funciones.js" type="text/javascript"></script>
</html>
