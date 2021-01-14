<%-- 
    Document   : usuarioForm
    Created on : Dec 20, 2020, 8:33:48 PM
    Author     : aarongarcia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp?de=usuarioForm.jsp" %>
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

            <div class="card-header bg-primary">
                <h1>
                    Datos de usuario
                </h1>
            </div>

            <div class="card-body">
                <form 
                    action="UserServlet?accion=guardar" 
                    method="POST" 
                    name="formUsuario" 
                    enctype="multipart/form-data"
                    id="formUsuario">
                   
                    <input 
                        class="form-control"
                        type="hidden" 
                        name="id" 
                        id="id" 
                        value="<c:out value="${dto.entidad.idUsuario}"/>"/>

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-form-label">Nombre:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                id="txtnombre"
                                type="text" 
                                name="txtnombre" 
                                class="form-control"
                                required="requiered" 
                                placeholder="Nombre de usuario"
                                value="<c:out value="${dto.entidad.nombre}"/>"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-form-label">Apellido paterno:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="text" 
                                name="txtpaterno" 
                                id="txtpaterno"
                                required="requiered" 
                                placeholder="Apellido paterno"
                                class="form-control"
                                value="<c:out value="${dto.entidad.paterno}"/>"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-form-label">Apellido materno:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="text" 
                                name="txtmaterno" 
                                id="txtmaterno"
                                required="requiered" 
                                placeholder="Apellido materno"
                                class="form-control"
                                value="<c:out value="${dto.entidad.materno}"/>"/>
                        </div>
                    </div>    

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class=" col-form-label">Correo electr&oacute;nico:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="email" 
                                name="txtemail" 
                                id="txtemail"
                                required="requiered" 
                                placeholder="Correo electrónico"
                                class="form-control"
                                value="<c:out value="${dto.entidad.email}"/>"/>
                        </div>
                    </div> 

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-form-label">Nombre usuario:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="text" 
                                name="txtnombreUsuario" 
                                id="txtnombreUsuario"
                                required="requiered" 
                                placeholder="Username"
                                class="form-control"
                                value="<c:out value="${dto.entidad.nombreUsuario}"/>"/>
                        </div>
                    </div> 

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-form-label">Clave de usuario:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="password" 
                                name="txtclaveUsuario" 
                                id="txtclaveUsuario"
                                required="requiered" 
                                placeholder="Password"
                                class="form-control"
                                value="<c:out value="${dto.entidad.claveUsuario}"/>"/>
                        </div>
                    </div>   

                    <div class="form-group row">
                        <div class="col-sm-2">
                            <label class="col-form-label">Tipo de usuario:</label>
                        </div>

                        <div class="col-sm-10">
                            <input 
                                type="text" 
                                name="txttipoUsuario" 
                                id="txttipoUsuario"
                                required="requiered" 
                                placeholder="Rol de usuario"
                                class="form-control"
                                value="<c:out value="${dto.entidad.tipoUsuario}"/>"/>
                        </div>
                    </div>  


                    <div class="form-group row">
                        <label for="imagen" class="col-sm-2 col-form-label">Imagen:</label>
                        <div class="col-sm-10">
                            <input 
                                type="file" 
                                class="form-control-file" 
                                id="imagen" 
                                name="imagen" 
                                accept="image/png, image/jpeg"
                                value="<c:out value="${dto.entidad.imagen}"/>"/>
                        </div>
                    </div>




                    <div class="col-sm-10">
                        <input 
                            type="hidden" 
                            name="txtimagen" 
                            id="txtimagen"
                            required="requiered" 
                            placeholder="Imagen de perfil"
                            class="form-control"
                            value="<c:out value="${dto.entidad.imagen}"/>"/>
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
