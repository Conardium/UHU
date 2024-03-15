<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>Artículos de Segunda Mano</title>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/estilo.css" />
    </head>

    <body>

        <div id="contenedor">
            <header id="cabecera">
                <h1>El Cajón de Satre</h1>
                <h2>Artículos de Segunda Mano</h2>
            </header>

            <%@include file="/WEB-INF/jspf/menu.jspf" %>

            <article id="principal">
                <h1>Favoritos </h1>
                <c:choose>
                    <c:when test="${!empty sessionScope.lart}">
                        <table border>
                            <tr>
                                <th>IMG</th>
                                <th>ID</th>
                                <th>CÓDIGO</th>
                                <th>NOMBRE</th>
                                <th>PRECIO</th>
                                <th colspan="2">Acciones</th>
                            </tr>
                            <c:set var="i" value="-1" />
                            <c:forEach var="art" items="${sessionScope.lart}">
                                <tr>
                                    <td><img width="100px" height="100px" src="img/${art.cod}.jpg" /></td>
                                    <td>${art.id}</td>
                                    <td>${art.cod}</td>
                                    <td>${art.nombre}</td>
                                    <td>${art.precio}</td>
                                    <td><a href="delFavorito?id=${i=i+1}">(-)</a></td>
                                    <td><a href="detalle?id=${art.id}">Detalle</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p>
                            <a href="invalidar">Borrar Todo</a>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p>No hay Artículos en favoritos</p>
                    </c:otherwise>
                </c:choose>
                <hr />
            </article>

            <footer id="pie">
                <p>&copy; DAW - UHU 2021</p>
            </footer>
        </div>


        <script type="text/javascript" src="js/funciones.js"></script>

    </body>
</html>
