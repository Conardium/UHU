
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>

<c:choose>
    <c:when test="${!empty requestScope.articulos}">
        <div class="container">
            <c:forEach var="art" items="${requestScope.articulos}">
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