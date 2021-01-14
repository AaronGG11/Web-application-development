<%-- 
    Document   : verUsuario
    Created on : Dec 20, 2020, 7:39:23 PM
    Author     : aarongarcia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page errorPage="error.jsp?de=verUsuario.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        
        <style>
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: red;
   color: white;
   text-align: center;
}
</style>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="/">Proyecto MVC</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="/">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="UserServlet?accion=listaDeUsuarios">Lista de usuarios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="UserServlet?accion=nuevo">Registrar usuario</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="UserServlet?accion=verpdf" target="blank">Ver pdf</a>
                        </li>
                    </ul>
                </div>
            </nav>


            <br>

            <div class="card bg-light">
                <div class="card-header">
                    Datos de usuario
                </div>
                <div class="card-body">
                    <ul class="list-group">

                        <li class="list-group-item"><strong>Id: </strong><c:out value="${usuario.entidad.idUsuario}"/></li>
                        <li class="list-group-item"><strong>Nombre: </strong><c:out value="${usuario.entidad.nombre}"/></li>
                        <li class="list-group-item"><strong>Apellido Paterno: </strong><c:out value="${usuario.entidad.paterno}"/></li>
                        <li class="list-group-item"><strong>Apellido Materno: </strong><c:out value="${usuario.entidad.materno}"/></li>
                        <li class="list-group-item"><strong>Correo electrónico: </strong><c:out value="${usuario.entidad.email}"/></li>
                        <li class="list-group-item"><strong>Nombre de usuario: </strong><c:out value="${usuario.entidad.nombreUsuario}"/></li>
                        <li class="list-group-item"><strong>Clave de usuario: </strong><c:out value="${usuario.entidad.claveUsuario}"/></li>
                        <li class="list-group-item"><strong>Tipo de usuario: </strong><c:out value="${usuario.entidad.tipoUsuario}"/></li>
                        <li class="list-group-item"><strong>Imagen: </strong><c:out value="${usuario.entidad.imagen}"/></li>
                    </ul>
                </div>
            </div>
                    
            <br>
            <a href="javascript:history.back()" class="btn btn-outline-dark btn-sm" role="button">Regresar</a>  

        </div>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
