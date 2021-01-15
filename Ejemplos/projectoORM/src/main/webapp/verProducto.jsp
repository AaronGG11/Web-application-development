<%-- 
    Document   : verProducto
    Created on : Dec 23, 2020, 1:55:15 PM
    Author     : aarongarcia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="/">Project MVC</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="/">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CategoriaServlet?accion=listaDeCategorias">Lista de categorias</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CategoriaServlet?accion=nuevo">Registrar categoria</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CategoriaServlet?accion=verpdf" target="blank">Ver pdf</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <br>

            <div class="card bg-light">
                <div class="card-header">
                    Datos de producto
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Id: </strong><c:out value="${producto.entidad.idProducto}"/></li>
                        <li class="list-group-item"><strong>Nombre: </strong><c:out value="${producto.entidad.nombreProducto}"/></li>
                        <li class="list-group-item"><strong>Descripci&oacute;n: </strong><c:out value="${producto.entidad.descripcionProducto}"/></li>
                        <li class="list-group-item"><strong>Precio: </strong><c:out value="${producto.entidad.precio}"/></li>
                        <li class="list-group-item"><strong>Existencia: </strong><c:out value="${producto.entidad.existencia}"/></li>
                        <li class="list-group-item"><strong>Categoria: </strong><c:out value="${producto.entidad.idCategoria}"/></li>
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
