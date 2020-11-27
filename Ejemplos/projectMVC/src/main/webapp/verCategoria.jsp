<%-- 
    Document   : ver
    Created on : Nov 26, 2020, 6:03:51 PM
    Author     : aarongarcia
--%>

<%@page import="com.escom.wad.model.dto.CategoriaDTO"%>
<%@page import="com.escom.wad.model.dao.CategoriaDAO"%>
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

            <%
                Integer id = Integer.parseInt(request.getParameter("id"));

                CategoriaDAO dao = new CategoriaDAO();
                CategoriaDTO dto = new CategoriaDTO();

                dto.getEntidad().setIdCategoria(id);

                dto = dao.read(dto);
            %>

            <div class="card bg-light">
                <div class="card-header">
                    Datos de categoria
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Id: </strong>
                            <%= dto.getEntidad().getIdCategoria() %>
                        </li>
                        <li class="list-group-item"><strong>Nombre: </strong>
                            <%= dto.getEntidad().getNombreCategoria() %>
                        </li>
                        <li class="list-group-item"><strong>Descripci&oacute;n: </strong>
                            <%= dto.getEntidad().getDescripcionCategoria() %>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>