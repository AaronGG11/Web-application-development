<%-- 
    Document   : listaUsuarios
    Created on : Dec 20, 2020, 2:33:59 PM
    Author     : aarongarcia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page errorPage="error.jsp?de=listaUsuarios.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de usuarios</title>
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
            <table class="table table-sm table-bordered table-hover ">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col" class="text-center">ID</th>
                        <th scope="col" class="text-center">Nombre</th>
                        <th scope="col" class="text-center">Paterno</th>
                        <th scope="col" class="text-center">Materno</th>
                        <th scope="col" class="text-center">Email</th>
                        <th scope="col" class="text-center">Usuario</th>
                        <th scope="col" class="text-center">Clave</th>
                        <th scope="col" class="text-center">Tipo</th>
                        <th scope="col" class="text-center">Imagen</th>
                        <th scope="col" class="text-center" colspan="3">Acci&oacute;n</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach 
                        var="usuarios"
                        items="${listaDeUsuarios}">

                        <tr>
                            <td class="text-center">
                                <a class="btn"
                                   href="UserServlet?accion=ver&id=<c:out value="${usuarios.entidad.idUsuario}"/>">
                                    <c:out value="${usuarios.entidad.idUsuario}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${usuarios.entidad.nombre}"/> <!--  -->
                            </td>
                            <td>
                                <c:out value="${usuarios.entidad.paterno}"/> <!--  -->
                            </td>
                            <td>
                                <c:out value="${usuarios.entidad.materno}"/> <!--  -->
                            </td>
                            <td>
                                <c:out value="${usuarios.entidad.email}"/> <!--  -->
                            </td>
                            <td>
                                <c:out value="${usuarios.entidad.nombreUsuario}"/> <!--  -->
                            </td>
                            <td>
                                <c:out value="${usuarios.entidad.claveUsuario}"/> <!--  -->
                            </td>
                            <td>
                                <c:out value="${usuarios.entidad.tipoUsuario}"/> <!--  -->
                            </td>
                            <td>
                                <!--<img   
                                    src="./imagenes_usuario/aqui"
                                    class="img-rounded" 
                                    width="80" 
                                    height="80"> -->
                                <c:out value="${usuarios.entidad.imagen}"/>
                            </td>  
                            <td class="text-center">
                                <a class="btn btn-danger btn-xs"
                                   href="UserServlet?accion=eliminar&id=<c:out value="${usuarios.entidad.idUsuario}"/>">
                                    <i class='bx bx-trash'></i> 
                                </a>
                            </td>
                            <td class="text-center">
                                <a class="btn btn-warning btn-xs"
                                   href="UserServlet?accion=actualizar&id=<c:out value="${usuarios.entidad.idUsuario}"/>">
                                    <i class='bx bx-pencil' ></i>
                                </a>
                            </td>   
                            <td class="text-center">
                                <a class="btn btn-info btn-xs"
                                   href="UserServlet?accion=reporteUsuario&id=<c:out value="${usuarios.entidad.idUsuario}"/>" target="blank">
                                    <i class='bx bxs-report'></i>
                                </a>
                            </td>   
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

