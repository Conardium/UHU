<%-- 
    Document   : altaRoles
    Created on : 14-nov-2022, 14:25:03
    Author     : ismae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Rol</h1>
        <form action="/javaWebApp2/roles/persistRol" method="POST">
            <label for="nRol">Rol: </label>
            <input type="text" id="nRol" name="nombre" /> <br />
            <input type="submit" value="Añadir Rol" />
        </form>

        <hr />
        <nav> |<a href="/javaWebApp2/"> Inicio </a> |
        </nav>
    </body>
</html>
