<%-- 
    Document   : register
    Created on : 19-dic-2022, 13:40:04
    Author     : ismae
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
            <img src="/PFdaw/img/icono_titulo.png" id="icono_logo" />
            GameSpace
        </div>

        <div class="contenedor-register">
            <h2>Registrarse</h2>
            <form method="POST" id="formRegister" onsubmit="return checkForm()" action="guardar">
                <c:if test="${requestScope.msgErrorRegister!=null}"><p class="errorLogin">${requestScope.msgErrorRegister}</p></c:if>
                
                <div class="contenedor-user">
                    <input id="usuario" type="text" name="usuario">
                    <label>Usuario:</label>
                    <p id="pNombre"></p>
                </div>
                
                <div class="contenedor-user">
                    <input id="correo" type="email" name="correo" onchange="comprobarCorreoValido();">
                    <label>Correo Electrónico:</label>
                    <p id="pCorreo"></p>
                </div>
                
                <div class="contenedor-user">
                    <input id="passw" type="password" name="passw">
                    <label>Contraseña:</label>
                    <p id="pClave"></p>
                </div>
                
                <div class="contenedor-user">
                    <input id="passw2" type="password" name="passw2">
                    <label>Repetir contraseña:</label>
                    <p id="pRepeClave"></p>
                </div>
                
                <div class="contenedor-user">
                    <input id="cp" type="text" name="cp" placeholder="Formato: 12345" pattern="[0-9]{5}">
                    <label>Codigo Postal:</label>
                    <p id="pCodigoPostal"></p>
                </div>
                
                <div class="contenedor-user">
                    <input id="telf" type="text" name="telf" placeholder="Formato: 123456789" pattern="[0-9]{9}">
                    <label>Teléfono:</label>
                    <p id="pTelefono"></p>
                </div>

                <div class="boton-enviar">
                    <input id="enviar" type="submit" value="Enviar">
                    <a id="volver" href="home">Volver</a>
                </div>
                <div id="iniciarSesion">
                    ¿Ya tienes una cuenta?
                    <a href="login">Inicia Sesión</a>
                </div>
            </form>
        </div>
    </body>
    <script src="/PFdaw/js/funciones.js" type="text/javascript"></script>
    <script src="/PFdaw/js/register.js" type="text/javascript"></script>
</html>
