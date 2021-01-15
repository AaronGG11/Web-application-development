<%-- 
    Document   : productoForm
    Created on : Dec 13, 2020, 9:46:34 PM
    Author     : aarongarcia
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp?de=listaDeCategorias.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>P&aacute;gina de formulario</title>
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

            <div class="card-header bg-primary">
                <h1>
                    Datos de producto
                </h1>
            </div>

            <div class="card-body">
                <form 
                    action="ProductoServlet?accion=guardar" 
                    method="POST" 
                    name="formProducto" 
                    id="formProducto">

                    <input 
                        class="form-control"
                        type="hidden" 
                        name="id" 
                        id="id" 
                        value="<c:out value="${dto.entidad.idProducto}"/>"/>

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-sm-2 col-form-label">Nombre:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                id="txtnombre"
                                type="text" 
                                name="txtnombre" 
                                class="form-control"
                                required="requiered" 
                                placeholder="Nombre de de producto"
                                value="<c:out value="${dto.entidad.nombreProducto}"/>"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-sm-2 col-form-label">Descripci&oacute;n:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="text" 
                                name="txtdescripcion" 
                                id="txtdescripcion"
                                required="requiered" 
                                placeholder="Descripci&oacute;n de producto"
                                class="form-control"
                                value="<c:out value="${dto.entidad.descripcionProducto}"/>"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-sm-2 col-form-label">Precio:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="text" 
                                name="txtprecio" 
                                id="txtprecio"
                                required="requiered" 
                                placeholder="Precio de producto"
                                class="form-control"
                                value="<c:out value="${dto.entidad.precio}"/>"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-sm-2 col-form-label">Existencia:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="text" 
                                name="txtexistencia" 
                                id="txtexistencia"
                                required="requiered" 
                                placeholder="Existencia de producto"
                                class="form-control"
                                value="<c:out value="${dto.entidad.existencia}"/>"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-sm-2 col-form-label" for="txtidcategoria">Categoria: </label>
                        </div>
                        <div class="col-sm-10">
                            <select class="form-control" id="txtidcategoria" name="txtidcategoria">
                                <c:if test="${dto.entidad.idProducto == null}">
                                    <option value="" selected disabled>Elige categoría</option>
                                </c:if>

                                <c:forEach 
                                    var="categorias"
                                    items="${listaDeCategorias}"
                                    >

                                    <c:choose>
                                        <c:when test="${dto.entidad.idCategoria == categorias.entidad.idCategoria}">
                                            <option value="<c:out value="${categorias.entidad.idCategoria}"/>" selected="selected"><c:out value="${categorias.entidad.nombreCategoria}" /></option>
                                        </c:when>    
                                        <c:otherwise>
                                            <option value="<c:out value="${categorias.entidad.idCategoria}"/>"><c:out value="${categorias.entidad.nombreCategoria}"/></option>
                                        </c:otherwise>
                                    </c:choose>


                                </c:forEach>

                            </select>
                        </div>
                    </div>

                    <button type="submit" class="form-group row offset-md-0 btn btn-outline-primary col-md-12">
                        Guardar                               
                    </button>
                </form>
            </div>
            <br>
            <a href="javascript:history.back()" class="btn btn-outline-dark btn-sm" role="button">Regresar</a>  
        </div>




        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>

