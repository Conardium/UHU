<%-- 
    Document   : altaUsuarios
    Created on : 27-nov-2022, 10:52:02
    Author     : ismae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    </head>
    <body>
        <h1>Alta Usuario</h1>
        <div>${requestScope.msg}<div>
                <form action="/javaWebApp2/usuarios/persistUser" method="POST">
                    <table border="1">
                        <tr> <td><label for="nameUser">Nombre: </label></td>
                            <td><input required type="text" id="nameUser" name="nombre" /></td> </tr>
                        <tr> <td><label for="emailUser">Correo: </label></td>
                            <td><input required type="email" id="emailUser" name="correo" /></td> </tr>
                        <tr>
                            <td><label for="rolUser">Rol: </label></td>
                            <td>
                                <c:forEach var="roles" items="${requestScope.roles}">
                                    <input id="${roles.id}" type="checkbox" name="roles" value="${roles.id}" />
                                    <label for=""${roles.id}>${roles.rol}</label><br />
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Crear Usuario" /></td>
                        </tr>
                    </table>
                </form>
                <hr />
                <p><a href="/javaWebApp2/">| Inicio |</a></p>
    </body>
</html>
