<%-- 
    Document   : alta
    Created on : 12-dic-2022, 12:07:51
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
        <h1>Alta Usuario</h1>
        <c:if test="${requestScope.msg!=null}"><p>${requestScope.msg}</p></c:if>
        <form method="POST" action="/userAuthApp/acceso/guardar" onsubmit="return chekcForm()">
            <table>
                <tr>
                    <td align="right">NICK:</td><td><input id="user" type="text" name="user"></td>
                </tr>
                <tr>
                    <td align="right">CLAVE: </td><td><input id="pwd1" type="password" name="pw1"></td>
                </tr>
                <tr>
                    <td align="right">REPETIR: </td><td><input id="pwd2" type="password" name="pw2"></td>
                </tr>               
                <tr>
                    <td></td><td><input type="submit" value="Alta"></td>
                </tr>
            </table>
        </form>
        <p><a href="/userAuthApp/acceso/home">Inicio</a></p>
        
        <script>
            function chekcForm() {
                var ok = true;
                var msg = "ERROR: ";
                var user = document.getElementById("user");
                var pass1 = document.getElementById("pwd1");
                var pass2 = document.getElementById("pwd2");
                if (user.value == "" || pass1.value == "" || pass2.value == "") {
                    msg = msg + "Todos los campos son obligatorios. ";
                    ok = false;
                }
                if (pass1.value !== pass2.value) {
                    msg = msg + "Passwords no coinciden. ";
                    ok = false;
                }
                if (!ok)
                    alert(msg);

                return ok;
            }
        </script>
        
    </body>
</html>
