<%-- 
    Document   : home
    Created on : 19-dic-2022, 12:50:25
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
        <link href="/PFdaw/css/home.css" rel="stylesheet" type="text/css"/>
        <link href="/PFdaw/css/seleccionados/homeSelec.css" rel="stylesheet" type="text/css"/>
        <link href="/PFdaw/css/juegos.css" rel="stylesheet" type="text/css"/>
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

            <section>
                <!-- Ultimos 3 articulos publicados -->
                <c:choose>
                        <c:when test="${!empty requestScope.ultimosPublicados}">
                            <h2 class="tittle-section">Últimas publicaciones</h2>
                            <div class="container">
                                <c:forEach var="art" items="${requestScope.ultimosPublicados}">
                                    <div class="card">
                                        <img src="/PFdaw/img/${art.nombre}${art.añoAdquisicion}.jpg">
                                        <h4>${art.nombre}</h4>
                                        <button>${art.genero}</button>
                                        <p>${art.precio}€</p>
                                        <a href="detalles?id=${art.id}">Más detalles</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise></c:otherwise>
                </c:choose>
                            
                <!-- Información sobre la aplicación -->
                <h2 class="about-title">Sobre la web:</h2>
                <p class="about">¡Bienvenido a nuestra tienda de juegos de segunda mano GameSpace! Aquí encontrarás una gran 
                variedad de títulos para todas las consolas, desde clásicos retro hasta los últimos lanzamientos. 
                Ofrecemos precios competitivos y una selección cuidadosa de juegos para asegurarnos de que encuentras 
                lo que buscas. Además, todos nuestros juegos son revisados y limpios antes de ser puestos a la venta. 
                Hacemos todo lo posible para asegurarnos de que estás satisfecho con tu compra. ¡Echa un vistazo a 
                nuestro catálogo en línea hoy mismo y encuentra tu próximo juego favorito!</p>
            </section>

            <footer>
                <p> &copy; Ismael Da Palma Fernández, DAW 2022-23 </p>
            </footer>
        </div>
    </body>
    <script src="/PFdaw/js/funciones.js" type="text/javascript"></script>
</html>
