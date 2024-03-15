
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
    </head>
    <body>
        <c:if test="${requestScope.msgDenegado!=null}"> 
            <h2>${requestScope.msgDenegado}</h2>
        </c:if>
    </body>
</html>
