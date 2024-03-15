<%-- 
    Document   : login
    Created on : 12-dic-2022, 11:48:39
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
        <h1>Identificación</h1>
        <c:if test="${requestScope.msg!=null}"><p>${requestScope.msg}</p></c:if>
        <form method="POST" action="/userAuthApp/acceso/validar">
            <table>
                <tr>
                    <td align="right">NICK:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td align="right">CLAVE: </td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Login"></td>
                </tr>
            </table>
        </form>
        <p><a href="/userAuthApp/acceso/home">Inicio</a></p>
    </body>
</html>
