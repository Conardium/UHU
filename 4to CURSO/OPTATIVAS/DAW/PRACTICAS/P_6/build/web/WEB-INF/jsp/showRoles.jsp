
<%@page import="java.util.List"%>
<%@page import="edu.daw.practica5.Roles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Roles</h1>
        <%
            List<Roles> lr = (List<Roles>) request.getAttribute("roles");
            if (lr.size() > 0) {
                out.println("<table border='1'>");
                for (Roles r : lr) {
                    out.println("<tr>");
                    out.println("<td>" + r.getRol() + "</td>");
                    out.println("<td><a href='/javaWebApp2/roles/delete?id=" + r.getId() + "'>Delete</a>");
                    out.println("<td><a href='/javaWebApp2/roles/edit?id=" + r.getId() + "'>Edit</a>");
                    out.println("</td>");

                }
                out.println("</table>");
            } else {
                out.println("<p>No hay Roles</p>");
            }

        %>
        <hr />
        <nav> |<a href="/javaWebApp2/"> Inicio </a>
            |<a href="/javaWebApp2/roles/altaRol"> Nuevo Rol </a> |
        </nav>    
    </body>
</html>
