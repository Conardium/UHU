<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>


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