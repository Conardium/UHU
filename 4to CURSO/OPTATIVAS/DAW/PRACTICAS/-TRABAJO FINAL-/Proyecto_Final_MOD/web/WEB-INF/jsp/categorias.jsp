
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
        <link href="/PFdaw/css/juegos.css" rel="stylesheet" type="text/css"/>
        <link href="/PFdaw/css/categorias.css" rel="stylesheet" type="text/css"/>
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
                <c:if test="${requestScope.msgDenegadoCat!=null}"> 
                    <p>${requestScope.msgDenegadoCat}</p> 
                    <div id = "equis">
                        <input type="button" onclick="cerrarMensaje()" value="X"/>
                    </div>
                </c:if>
            </div>

            <div id="msgCorrectos">
                <c:if test="${requestScope.msgOKCat!=null}"> 
                    <p>${requestScope.msgOKCat}</p> 
                    <div id = "equisOK">
                        <input type="button" onclick="cerrarMensaje()" value="X"/>
                    </div>
                </c:if>
            </div>

            <nav>
                <jsp:include page="menu.jsp"></jsp:include>
                </nav>

                <section>
                    <!-- Formulario para crear una categoria -->
                    <form method="POST" id="formCategorias" onsubmit="return checkCategoria();" action="/PFdaw/Categorias/addCategoria">
                        <fieldset>
                            <legend>Nueva Categoria</legend>
                            <label>Nombre:</label>
                            <input id="nombre" type="text" name="nombre">
                            <p id="pNombre"></p>
                            <input id="guardar" type="submit" value="Guardar">
                        </fieldset>
                    </form>

                    <!-- Estado de las inserciones -->

                    <!-- Listado de las categorias -->
                    <h2>Categorias</h2>
                    <div>
                    <c:choose>
                        <c:when test="${!empty requestScope.categorias}">
                            <table>
                                <tr>
                                    <th>Id</th>
                                    <th>Nombre</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <c:forEach var="categoria" items="${requestScope.categorias}">
                                    <tr>
                                        <td>${categoria.id}</td>
                                        <td>${categoria.name}</td>
                                        <td><a href="/PFdaw/Categorias/delCategoria?id=${categoria.id}" onclick="return validarBorrado();">Borrar</a></td>
                                        <td><a href="/PFdaw/Categorias/editCategoria?id=${categoria.id}">Editar</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <p>No hay ninguna categoria creada todavía</p>
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
    <script src="/PFdaw/js/categorias.js" type="text/javascript"></script>
    <script src="/PFdaw/js/funciones.js" type="text/javascript"></script>
</html>
