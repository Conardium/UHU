<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<ul class = "menu">
    <li><a href="home" id="aHome">Home</a></li>
    <li><a href="juegos" id="aJuegos">Juegos</a></li>

    <c:choose>
        <c:when test="${sessionScope.user!=null}">
            <li><a href="publicarJuego" id="aPublicarJuegos">Publicar Juego</a></li>
            <li><a href="favoritos" id="aFavoritos">Mis favoritos</a></li>
            <li><a href="logout">Logout</a></li>
            </c:when>
            <c:otherwise> 
            <li><a href="login">Login</a></li>
            <li><a href="register">Registrarse</a></li>
            </c:otherwise>
        </c:choose>
</ul>