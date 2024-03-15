
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

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
                
                <div>${msg}</div>

                <h1>Listado de Artículos</h1>
                <div>
                    <form>
                        <fieldset>
                            <legend>Filtro</legend>
                            Nombre: <input id="name" type="text" name="nombre" /> |
                            Precio: <input id="pmenor" type="text" name="menor" size="4" /> -
                            <input id="pmayor" type="text" name="mayor" size="4" /> |
                            <input type="button" value="Filtar" onclick="filtrar();"/>
                            <input type="reset" value="borrar" />
                        </fieldset>
                    </form>

                </div>
                <div id="respuesta">
                    <c:choose>
                        <c:when test="${!empty requestScope.articulos}">
                            <table style="width: 100%; border: 1px solid">
                                <tr>
                                    <th>IMG</th>
                                    <th>ID</th>
                                    <th>CÓDIGO</th>
                                    <th>NOMBRE</th>
                                    <th>PRECIO</th>
                                    <th colspan="3">Acciones</th>
                                </tr>
                                <c:forEach var="art" items="${requestScope.articulos}">
                                    <tr>
                                        <td><img width="100px" height="100px" src="img/${art.cod}.jpg" /></td>
                                        <td>${art.id}</td>
                                        <td>${art.cod}</td>
                                        <td>${art.nombre}</td>
                                        <td>${art.precio}</td>
                                        <td><a href="detalles?id=${art.id}">Detalle</a></td>
                                        <td><a href="addFavorito?id=${art.id}">+Favorito</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>No hay Artículos</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </article>

            <footer id="pie">
                <p>&copy; DAW - UHU 2021</p>
            </footer>
        </div>


        <script type="text/javascript" src="js/funciones.js"></script>

    </body>
</html>
