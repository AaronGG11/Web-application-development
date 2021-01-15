<%-- 
    Document   : listaProductos
    Created on : Dec 13, 2020, 9:47:13 PM
    Author     : aarongarcia
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page errorPage="error.jsp?de=listaDeProductos.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de productos</title>
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
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
                            <a class="nav-link" href="ProductoServlet?accion=listaDeProductos">Lista de productos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ProductoServlet?accion=nuevo">Registrar producto</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ProductoServlet?accion=verpdf" target="blank">Ver pdf</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <table class="table table-sm table-bordered table-hover ">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col" class="text-center">Id</th>
                        <th scope="col" class="text-center">Nombre</th>
                        <th scope="col" class="text-center">Descripci&oacute;n</th>
                        <th scope="col" class="text-center">Precio</th>
                        <th scope="col" class="text-center">Existencia</th>
                        <th scope="col" class="text-center">Categoria</th>
                        <th scope="col" class="text-center" colspan="3">Acci&oacute;n</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach 
                        var="productos"
                        items="${listaDeProductos}">

                        <tr>
                            <td class="text-center">
                                <a class="btn"
                                   href="ProductoServlet?accion=ver&id=<c:out value="${productos.entidad.idProducto}"/>">
                                    <c:out value="${productos.entidad.idProducto}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${productos.entidad.nombreProducto}"/> <!--  -->
                            </td>
                            <td>
                                <c:out value="${productos.entidad.descripcionProducto}"/> <!--  -->
                            </td>
                            <td class="text-center">
                                <c:out value="${productos.entidad.precio}"/> <!--  -->
                            </td>
                            <td class="text-center">
                                <c:out value="${productos.entidad.existencia}"/> <!--  -->
                            </td>
                            <td class="text-center">
                                <c:out value="${productos.entidad.idCategoria}"/> <!--  -->
                            </td>
                            <td class="text-center">
                                <a class="btn btn-danger btn-xs"
                                   href="ProductoServlet?accion=eliminar&id=<c:out value="${productos.entidad.idProducto}"/>">
                                    <i class='bx bx-trash'></i> 
                                </a>
                            </td>
                            <td class="text-center">
                                <a class="btn btn-warning btn-xs"
                                   href="ProductoServlet?accion=actualizar&id=<c:out value="${productos.entidad.idProducto}"/>">
                                    <i class='bx bx-pencil' ></i>
                                </a>
                            </td>
                            <td class="text-center">
                                <a class="btn btn-info btn-xs"
                                   href="ProductoServlet?accion=reporteProducto&id=<c:out value="${productos.entidad.idProducto}"/>" target="blank">
                                    <i class='bx bxs-report'></i>
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

