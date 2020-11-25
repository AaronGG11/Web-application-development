<%-- 
    Document   : categoriaForm
    Created on : Nov 24, 2020, 11:12:00 AM
    Author     : aarongarcia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <a class="nav-link" href="CategoriaServlet?accion=guardar">Registrar categoria</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="card-header bg-primary">
                <h1>
                    Datos de la categoria
                </h1>
            </div>

            <div class="card-body">
                <form 
                    action="CategoriaServlet?accion=guardar" 
                    method="post" 
                    name="formCategoria" 
                    id="formCategoria">

                    <input 
                        type="hidden" 
                        name="id" 
                        id="id" 
                        value="<c:out value="${dto.entidad.idCategoria}"/>"/>

                    <div class="form-group row">
                        <div class="col-sm-6">
                            <label class="col-sm-2 col-form-label">Nombre</label>
                        </div>

                        <div class="col-sm-6">
                            <input 
                                type="text" 
                                name="txtnombre" 
                                id="txtnombre"
                                max="50" 
                                required="requiered" 
                                placeholder="Nombre de categoria"
                                class="form-control"
                                value="<c:out value="${dto.entidad.nombreCategoria}"/>"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-6">
                            <label class="col-sm-2 col-form-label">Descripcion</label>
                        </div>

                        <div class="col-sm-6">
                            <input 
                                type="text" 
                                name="txtdescripcion" 
                                id="txtdescripcion"
                                max="50" 
                                required="requiered" 
                                placeholder="Descripcion de categoria"
                                class="form-control"
                            value="<c:out value="${dto.entidad.descripcionCategoria}"/>"/>
                        </div>
                    </div>
            </div>

        </form>
    </div>



</div>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>