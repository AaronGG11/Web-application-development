<%-- 
    Document   : categoriaForm
    Created on : Nov 24, 2020, 11:12:00 AM
    Author     : aarongarcia
--%>

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

            <div class="card-header bg-primary">
                <h1>
                    Datos de categoria
                </h1>
            </div>

            <div class="card-body">
                <form 
                    action="CategoriaServlet?accion=guardar" 
                    method="POST" 
                    name="formCategoria" 
                    id="formCategoria">

                    <input 
                        class="form-control"
                        type="hidden" 
                        name="id" 
                        id="id" 
                        value="<c:out value="${dto.entidad.idCategoria}"/>"/>

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
                                placeholder="Nombre de categoria"
                                value="<c:out value="${dto.entidad.nombreCategoria}"/>"/>
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
                                placeholder="Descripci&oacute;n de categoria"
                                class="form-control"
                                value="<c:out value="${dto.entidad.descripcionCategoria}"/>"/>
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
