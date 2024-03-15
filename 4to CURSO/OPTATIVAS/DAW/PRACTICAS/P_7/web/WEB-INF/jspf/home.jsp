
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
                <h3>Nuevo Artículo</h3>
                <form action="creaArticulo" method="POST" enctype="multipart/form-data">
                    <table>
                        <tr>
                            <td><label for="id">Id:</label></td>
                            <td>
                                <input id="id" type="text" name="id" onchange="validarIdDB();"/>
                        <span id="idvalido"></span>
                        </td>
                        </tr>
                        <tr>
                            <td><label for="cod">Código:</label></td>
                            <td><input id="cod" type="text" name="cod" /></td>
                        </tr>
                        <tr>
                            <td><label for="name">Nombre:</label></td>
                            <td><input id="name" type="text" name="nombre" /></td>
                        </tr>
                        <tr>
                            <td><label for="price">Precio:</label></td>
                            <td><input id="price" type="text" name="precio" /></td>
                        </tr>
                        <tr>
                            <td>Imagen:</td>
                            <td><input type="file" name="file" value="" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Crear Artículo" /></td>
                        </tr>
                    </table>                
                </form>
            </article>

            <footer id="pie">
                <p>&copy; DAW - UHU 2021</p>
            </footer>
        </div>


        <script type="text/javascript" src="js/funciones.js"></script>

    </body>
</html>
