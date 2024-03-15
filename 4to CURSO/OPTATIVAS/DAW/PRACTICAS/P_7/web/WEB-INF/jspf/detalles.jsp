
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>Artículos de Segunda Mano</title>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="css/estilo.css" />
    </head>

    <body>

        <div id="contenedor">
            <header id="cabecera">
                <h1>El Cajón de Satre</h1>
                <h2>Artículos de Segunda Mano</h2>
            </header>

            <%@include file="/WEB-INF/jspf/menu.jspf" %>

            <article id="principal">
                <div>${msg}</div>

                <h1>Detalles del Artículo</h1>

                <p><img src="img/${requestScope["articulo"].cod}.jpg" width="100" height="100" />
                <p>Id: ${requestScope["articulo"].id}</p>
                <p>Código: ${requestScope["articulo"].cod}</p>
                <p>Nombre: ${requestScope["articulo"].nombre}</p>
                <p>precio: ${requestScope["articulo"].precio}</p>
            </article>

            <footer id="pie">
                <p>&copy; DAW - UHU 2021</p>
            </footer>
        </div>


        <script type="text/javascript" src="js/funciones.js"></script>

    </body>
</html>
