<%-- 
    Document   : favoritos
    Created on : 10-ene-2023, 9:04:03
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
        <link href="/PFdaw/css/juegos.css" rel="stylesheet" type="text/css"/>
        <link href="/PFdaw/css/seleccionados/favoritosSelec.css" rel="stylesheet" type="text/css"/>
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

            <nav>
                <jsp:include page="menu.jsp"></jsp:include>
                </nav>

                <section>
                    <div>
                    <c:choose>
                        <c:when test="${!empty sessionScope.lartFav}">
                            <div class="container">
                                <c:forEach var="art" items="${sessionScope.lartFav}">
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
                        <c:otherwise>
                            <div class="articulosVacios">
                                <div>
                                    <img src="/PFdaw/img/articulosVacios.png" id="artVacios"/>
                                    <p>Ups, no hay artículos</p>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>

            <footer>
                <p> &copy; Ismael Da Palma Fernández, DAW 2022-23 </p>
            </footer>
        </div>
    </body>
    <script src="/PFdaw/js/funciones.js" type="text/javascript"></script>
</html>

