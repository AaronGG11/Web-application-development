<%-- 
    Document   : index
    Created on : Nov 22, 2020, 3:32:10 PM
    Author     : aarongarcia
--%>

<%@page import="com.escom.wad.model.dto.CategoriaDTO"%>
<%@page import="com.escom.wad.model.dao.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>P&aacute;gina de inicio</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <%
                CategoriaDAO dao = new CategoriaDAO();
                CategoriaDTO dto = new CategoriaDTO();
                
                dto.getEntidad().setNombreCategoria(request.getParameter("txtnombre"));
                dto.getEntidad().setDescripcionCategoria(request.getParameter("txtdescripcion"));

                // nuevo elemento
                if(request.getParameter("id") == null){
                    dao.create(dto);
                }else{ // actualizacion
                    dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
                    dao.update(dto);
                }
            %>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
