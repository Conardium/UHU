<%-- 
    Document   : index
    Created on : 12-dic-2022, 11:50:53
    Author     : ismae
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${requestScope.msg!=null}"><p>${requestScope.msg}</p></c:if>
            <h1>Hello World App!</h1>

        <c:if test="${sessionScope.user!=null}"> Hola ${sessionScope.user}</c:if>

            <h3>Menú</h3>
            <p><a href="/userAuthApp/acceso/home">Inicio</a></p>
        <c:choose>
            <c:when test="${sessionScope.user!=null}">
                <p><a href="/userAuthApp/acceso/privado">Privado</a></p>
                <p><a href="/userAuthApp/acceso/logout">Logout</a></p>
            </c:when>
            <c:otherwise> 
                <p><a href="/userAuthApp/acceso/alta">Alta</a></p>
                <p><a href="/userAuthApp/acceso/login">Login</a></p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
