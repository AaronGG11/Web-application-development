<%-- 
    Document   : eliminarCategoria
    Created on : Nov 23, 2020, 8:48:42 PM
    Author     : aarongarcia
--%>

<%@page import="com.escom.wad.model.dto.CategoriaDTO"%>
<%@page import="com.escom.wad.model.dao.CategoriaDAO"%>
<%@page errorPage="error.jsp?de=listaDeCategorias.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar categoria</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    </head>
    <body>
        <div class="container">
            <h1>Eliminar categoria</h1>
            
            <%
                // continuar la evolucion de los modelos de desarrollo hasta terminar en mvc
                Integer id = Integer.parseInt(request.getParameter("id"));
                
                CategoriaDAO dao = new CategoriaDAO();
                CategoriaDTO dto = new CategoriaDTO();
                
                dto.getEntidad().setIdCategoria(id);
                
                dao.delete(dto);
                
                out.println("Categoria id=  " + id + " eliminada correctamente");
            %>
        </div>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
