<%-- 
    Document   : login
    Created on : 19-dic-2022, 12:43:45
    Author     : ismae
--%>

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
        <link href="/PFdaw/css/login-register.css" rel="stylesheet" type="text/css"/>
    </head>

    <body class="fondo">

        <div class="logo">
            <img src="/PFdaw/img/icono_titulo.png" id="icono_logo"/>
            GameSpace
        </div>

        <div class="contenedor-login">
            <h2>Login</h2>
            <form method="POST" action="validar">
                <c:if test="${requestScope.msgErrorLogin!=null}"><p class="errorLogin">${requestScope.msgErrorLogin}</p></c:if>
                <c:if test="${requestScope.msgOKRegister!=null}"><p class="okRegister">${requestScope.msgOKRegister}</p></c:if>

                <div class="contenedor-user">
                    <input id="usuario" type="text" name="usuario" placeholder="Correo Electronico">
                    <label>Usuario:</label>
                </div>
                <div class="contenedor-user">
                    <input id="passw" type="password" name="passw">
                    <label>Contraseña:</label>
                </div>

                <div class="boton-enviar">

                    <input id="enviar" type="submit" value="Enviar">
                    <a id="volver" href="home">Volver</a>
                </div>



                <div id="registrar">
                    ¿No tienes una cuenta?
                    <a href="register">Regístrate</a>
                </div>
            </form>
        </div>
    </body>

</html>
