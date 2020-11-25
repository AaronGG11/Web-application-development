<%-- 
    Document   : listaCategorias
    Created on : Nov 24, 2020, 11:08:25 AM
    Author     : aarongarcia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page errorPage="error.jsp?de=listaDeCategorias.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de categorias</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="/projectMVC/">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CategoriaServlet?accion=listaDeCategorias">Lista de categorias</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="CategoriaServlet?accion=nuevo">Registrar categoria</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <table class="table table-sm table-bordered table-striped table-hover ">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col" class="text-center">Clave</th>
                        <th scope="col" class="text-center">Nombre</th>
                        <th scope="col" class="text-center">Descripci&oacute;n</th>
                        <th scope="col" class="text-center" colspan="2">Acci&oacute;n</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach 
                        var="categorias"
                        items="${listaDeCategorias}">

                        <tr>
                            <td>
                                <a class="btn btn-primary btn-xs"
                                   href="CategoriaServlet?accion=ver&id=<c:out value="${categorias.entidad.idCategoria}"/>">
                                    <c:out value="${categorias.entidad.idCategoria}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${categorias.entidad.nombreCategoria}"/> <!--  -->
                            </td>
                            <td>
                                <c:out value="${categorias.entidad.descripcionCategoria}"/> <!--  -->
                            </td>
                            <td>
                                <a class="btn btn-danger btn-xs"
                                   href="CategoriaServlet?accion=eliminar&id=<c:out value="${categorias.entidad.idCategoria}"/>">
                                    Eliminar 
                                </a>
                            </td>
                            <td>
                                <a class="btn btn-warning btn-xs"
                                   href="CategoriaServlet?accion=actualizar&id=<c:out value="${categorias.entidad.idCategoria}"/>">
                                    Actualizar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
