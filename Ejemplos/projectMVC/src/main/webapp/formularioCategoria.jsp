<%-- 
    Document   : formularioCategoria
    Created on : Nov 26, 2020, 4:04:47 PM
    Author     : aarongarcia
--%>

<%@page import="com.escom.wad.model.dto.CategoriaDTO"%>
<%@page import="com.escom.wad.model.dao.CategoriaDAO"%>
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
            <%
                CategoriaDAO dao = new CategoriaDAO();
                CategoriaDTO dto = new CategoriaDTO();
                
                Boolean bandera = Boolean.FALSE;
                
                if(request.getParameter("id") != null ){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    dto.getEntidad().setIdCategoria(id);
                    dto = dao.read(dto);
                    bandera = Boolean.TRUE;
                }
            %>
            

            <div class="card-header bg-primary">
                <h1>
                    Datos de categoria
                </h1>
            </div>

            <div class="card-body">
                <form 
                    action="guardar.jsp" 
                    method="POST" 
                    name="formCategoria" 
                    id="formCategoria">

                    <div class="form-group row">
                        
                        <div class="col-sm-2">
                            <label class="col-sm-2 col-form-label">Nombre:</label>
                        </div>
                        
                        <input 
                        class="form-control"
                        type="hidden" 
                        name="id" 
                        id="id" 
                        value="<%=dto.getEntidad().getIdCategoria()%>"/>

                        <div class="col-sm-10">
                            <input 
                                id="txtnombre"
                                type="text" 
                                name="txtnombre" 
                                class="form-control"
                                required="requiered" 
                                placeholder="Nombre de categoria"
                                <%
                                    if(bandera){// actualizacion
                                        out.println("value='" + dto.getEntidad().getNombreCategoria() + "'");
                                    }
                                %>
                                />
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
                                <%
                                    if(bandera){// actualizacion
                                        out.println("value='" + dto.getEntidad().getDescripcionCategoria() + "'");
                                    }
                                %>
                                />
                        </div>
                    </div>

                    <button type="submit" class="form-group row offset-md-0 btn btn-outline-primary col-md-12">
                        Guardar                               
                    </button>
                </form>
            </div>
        </div>




        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>