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
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />
    </head>
    <body>        
        <div class="container">
            <h1>P&aacute;gina de lista de categorias</h1>
            <div style='margin-bottom:12px; margin-top: 20px' class="row">
                <div class="col-sm-2">
                    <a href="formularioCategoria.jsp" class='btn btn-success'><i class='bx bx-add-to-queue'></i> Nueva categor&iacute;a</a>
                </div>
                <div class="col-sm-10">
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Maecenas ultricies mi eget mauris pharetra et ultrices. Iaculis nunc sed augue lacus viverra vitae congue.</p>
                </div>
                
                
                
            </div>
         
            <table class="table table-sm table-bordered table-hover ">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col" class="text-center">Clave</th>
                        <th scope="col" class="text-center">Nombre</th>
                        <th scope="col" class="text-center">Descripci&oacute;n</th>
                        <th scope="col" class="text-center">Acci&oacute;n</th>
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
                        <th class="text-center">
                            
                            <a href="verCategoria.jsp?id=<%= dto.getEntidad().getIdCategoria() %>">
                                <%= dto.getEntidad().getIdCategoria() %>
                            </a>
                        </th>
                        <td>
                            <%= dto.getEntidad().getNombreCategoria() %>
                        </td>
                        <td>
                            <%= dto.getEntidad().getDescripcionCategoria() %>
                        </td>
                        <td class="text-center">
                            <a href="eliminarCategoria.jsp?id=<%=dto.getEntidad().getIdCategoria()%>" class='btn btn-danger'><i class='bx bxs-trash-alt'></i></a>
                            <a href="formularioCategoria.jsp?id=<%=dto.getEntidad().getIdCategoria()%>" class='btn btn-warning'><i class='bx bxs-pencil'></i></a>
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
