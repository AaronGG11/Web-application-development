<%-- 
    Document   : listaDeCategorias
    Created on : Nov 22, 2020, 4:01:46 PM
    Author     : aarongarcia
--%>

<%@page import="com.escom.wad.model.dto.CategoriaDTO" %>
<%@page import="com.escom.wad.model.dao.CategoriaDAO" %>
<%@page import="java.util.List" %>

<%@page errorPage="error.jsp?de=listaDeCategorias.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de categorias</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    </head>
    <body>        
        <div class="container">
            <h1>P&aacute;gina de lista de categorias</h1><br>
            <table class="table table-sm table-bordered table-striped table-hover ">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Clave</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Descripci&oacute;n</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        CategoriaDAO dao = new CategoriaDAO();
                        List lista = dao.readAll();

                        if (lista != null) {
                            for (int i = 0; i < lista.size(); i++) {
                                CategoriaDTO dto = (CategoriaDTO)lista.get(i);
                    %>

                    <tr>
                        <th>
                            <%= dto.getEntidad().getIdCategoria() %>
                        </th>
                        <td>
                            <%= dto.getEntidad().getNombreCategoria() %>
                        </td>
                        <td>
                            <%= dto.getEntidad().getDescripcionCategoria() %>
                        </td>
                    </tr>
                    
                    <%
                            }
                        }else{      
                    %>
                    
                    <tr>
                        <th colspan="3" scope="row">
                            No hay registros en base de datos
                        </th>
                    </tr>
                    
                    <%
                        }     
                    %>

                </tbody>
            </table>
        </div>




        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
