<%-- 
    Document   : error
    Created on : Nov 22, 2020, 3:40:22 PM
    Author     : aarongarcia
--%>

<%@page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>P&aacute;gina de error</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    </head>
    <body>
        <%
            String de = request.getParameter("de");
        %>
        
        
        <div class="container">
            <h1>P&aacute;gina de errores</h1>
            <div class="alert alert-danger" role="alert">
                <p>
                    Ocurrio un error en:  <%= de%>
                </p>

                <p>
                    Error detectado: <%= exception%>
                </p>

            </div>
        </div>

        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
