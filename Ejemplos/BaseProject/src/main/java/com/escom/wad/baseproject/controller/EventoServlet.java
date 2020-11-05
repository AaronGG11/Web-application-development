/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.baseproject.controller;

import com.escom.wad.baseproject.dao.EventoDAO;
import com.escom.wad.baseproject.model.Evento;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aarongarcia
 */
@WebServlet(name = "EventoServlet2710", urlPatterns = {"/EventoServlet2710"})
public class EventoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if(accion.equals("listaDeEventos")){
            listaDeEventos(request, response);
        }else{
            if(accion.equals("nuevo")){
                nuevoEvento(request, response);
            }else{
                if(accion.equals("eliminar")){
                    eliminarEvento(request, response);
                }else{
                    if(accion.equals("actualizar")){
                        actualizarEvento(request, response);
                    }else{
                        if(accion.equals("guardar")){
                            almacenarEvento(request, response);
                        }else{
                            if(accion.equals("ver")){
                                verEvento(request, response);
                            }
                        }
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaDeEventos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de eventos</title>"); 
            out.println("<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class=\"jumbotron jumbotron-fluid\">");
                out.println("<div class=\"container\">");
                   out.println("<h1>ESCOM Eventos</h1>");
                out.println("</div>");
            out.println("</div>");
            
            out.println("<div class='container'>");
            
                out.println("<div style='margin-bottom:12px'>");
                    out.println("<h3>Lista de eventos</h3>");
                    out.println("<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Maecenas ultricies mi eget mauris pharetra et ultrices. Iaculis nunc sed augue lacus viverra vitae congue. Amet consectetur adipiscing elit duis tristique sollicitudin nibh. Velit sed ullamcorper morbi tincidunt ornare massa. Odio tempor orci dapibus ultrices in iaculis nunc. Eu lobortis elementum nibh tellus molestie nunc non.</p>");
                    out.println("<a href='EventoServlet?accion=nuevo' class='btn btn-success'> <i class='bx bx-add-to-queue'></i> Nuevo Evento</a>");
                out.println("</div>");
            

            out.println("<table class='table table-hover table-bordered '>");

            out.println("<tr>");
            
            out.println("<thead class='thead-dark'>");
            
            out.println("<th class='text-center'>");
            out.println("Clave de Evento");
            out.println("</th>");

            out.println("<th class='text-center'>");
            out.println("Nombre del Evento");
            out.println("</th>");

            out.println("<th class='text-center'>");
            out.println("Sede");
            out.println("</th>");

            out.println("<th class='text-center'>");
            out.println("Fecha de Inicio");
            out.println("</th>");

            out.println("<th class='text-center'>");
            out.println("Fecha de Término");
            out.println("</th>");

            out.println("<th class='text-center'>");
            out.println("Acciones");
            out.println("</th>");
            
            out.println("</tr>");
            
            out.println("</thead>");

            int idEvento;
            String nombreEvento;
            String sede;
            Date fechaInicio;
            Date fechaTermino;

            EventoDAO dao = new EventoDAO();
            try {
                List lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    Evento e = (Evento) lista.get(i);
                    idEvento = e.getIdEvento();
                    nombreEvento = e.getNombreEvento();
                    sede = e.getSede();
                    fechaInicio = e.getFechaInicio();
                    fechaTermino = e.getFechaFin();
                    
                    out.println("<tr>");
                    out.println("<td class='text-center'>"+idEvento+"</td>");
                    out.println("<td>"+nombreEvento+"</td>");
                    out.println("<td>"+sede+"</td>");
                    out.println("<td class='text-center'>"+fechaInicio+"</td>");
                    out.println("<td class='text-center'>"+fechaTermino+"</td>");
                    
                    out.println("<td class='text-center'>");
                    // Ocultar mediante un formulario para no ver id, si no quiero mostrar nada
                    out.println("<a href='EventoServlet?accion=eliminar&id=" + idEvento +"' class='btn btn-danger' > <i class='bx bx-trash'></i> </a>");
      
                    out.println("<a href='EventoServlet?accion=actualizar&id=" + idEvento +"' class='btn btn-warning' > <i class='bx bx-pencil' ></i> </a>");
                    out.println("<a href='EventoServlet?accion=ver&id=" + idEvento +"' class='btn btn-info' > <i class='bx bx-show' ></i> </a>");
                   
                    out.println("</td>");
                    
                    out.println("</tr>");
                }
            } catch (SQLException e) {
                
            }

            out.println("</table>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            

        }
    }

    private void nuevoEvento(HttpServletRequest request, HttpServletResponse response) {
        try {
            //response.sendRedirect("eventosForm.html");
            RequestDispatcher vista = request.getRequestDispatcher("eventosForm.html");
            vista.forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Capturar los posibles valores 
        EventoDAO dao = new EventoDAO();
        Evento e = new Evento();
        
        try {
            e.setIdEvento(Integer.parseInt(request.getParameter("id")));
            dao.delete(e);
            
            response.sendRedirect("EventoServlet?accion=listaDeEventos");
            
            //listaDeEventos(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {        

        EventoDAO dao = new EventoDAO();
        Evento e = new Evento();
        
        try {
            e.setIdEvento(Integer.parseInt(request.getParameter("id")));
            e = dao.read(e);
            
            
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Datos de evento</title>"); 
            out.println("<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class=\"jumbotron jumbotron-fluid\">");
                out.println("<div class=\"container\">");
                   out.println("<h1>ESCOM Eventos</h1>");
                out.println("</div>");
            out.println("</div>");
            
            out.println("<div class='container'>");
            
            
            
                out.println("<div class='row justify-content-center'>");
                out.println("<div class='col-md-8'>");
                    out.println("<div class='card'>");
                        out.println("<div class='card-header'>Actualizar evento</div>");
                        out.println("<div class='card-body'>");
                            out.println("<form method='POST' action='EventoServlet?accion=guardar'>");
                                
                                out.println("<div class='form-group row'>");
                                    out.println("<label for='idevento' class='col-md-4 col-form-label text-md-right'>Identificador</label>");
                                    out.println("<div class='col-md-6'>");
                                        out.println("<input id='idevento' type='text' class='form-control 'name='idevento' placeholder='"+ e.getIdEvento() +"'  required autofocus readonly/>");
                                    out.println("</div>");
                                out.println("</div>");
                            
                                out.println("<div class='form-group row'>");
                                    out.println("<label for='nombreEvento' class='col-md-4 col-form-label text-md-right'>Nombre:</label>");
                                    out.println("<div class='col-md-6'>");
                                        out.println("<input id='nombreEvento' type='text' class='form-control 'name='nombreEvento' value='"+e.getNombreEvento()+"' required autofocus />");
                                    out.println("</div>");
                                out.println("</div>");
                                out.println("<div class='form-group row'>");
                                    out.println("<label for='sede' class='col-md-4 col-form-label text-md-right'>Sede: </label>");
                                    out.println("<div class='col-md-6'>");
                                        out.println("<input id='sede' type='text' class='form-control 'name='sede' value='"+e.getSede()+"'required autofocus />");
                                    out.println("</div>");
                                out.println("</div>");
                                out.println("<div class='form-group row'>");
                                    out.println("<label for='fechaInicio' class='col-md-4 col-form-label text-md-right'>Fecha Inicio:</label>");
                                    out.println("<div class='col-md-6'>");
                                        out.println("<input class='form-control' type='date' name='fechaInicio' id='fechaInicio'value='"+e.getFechaInicio()+"'>");
                                    out.println("</div>");
                                out.println("</div>");
                                out.println("<div class='form-group row'>");
                                    out.println("<label for='fechaFin' class='col-md-4 col-form-label text-md-right'>Fecha Término:</label>");
                                    out.println("<div class='col-md-6'>");
                                        out.println("<input class='form-control' type='date' name='fechaFin' id='fechaFin' value='"+e.getFechaFin()+"'>");
                                    out.println("</div>");
                                out.println("</div>");
                                out.println("<button type='submit' class='form-group row offset-md-2 btn btn-outline-primary col-md-8'>Actualizar</button>");
                            out.println("</form>");
                        out.println("</div>");
                    out.println("</div>");
                out.println("</div>");
            out.println("</div>");
            
            
            
            
            out.println("<a href='EventoServlet?accion=listaDeEventos' class='btn btn-outline-success'> <i class='bx bx-arrow-back'></i> Regresar </a>");
            
            out.println("</div>");            
            out.println("</body>");
            out.println("</html>");
            // tarea terminar fucnionalidad para actualizar un evento 
        }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
            //int id = Integer.parseInt(request.getParameter("id"));
            
            Evento e = new Evento();
            EventoDAO dao = new EventoDAO();
            if(request.getParameter("id") == null || request.getParameter("id").isEmpty()){
               e.setNombreEvento(request.getParameter("nombreEvento"));
               e.setSede(request.getParameter("sede"));
               e.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
               e.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));

                try {
                    dao.create(e);
                    
                    response.sendRedirect("EventoServlet?accion=listaDeEventos");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                e.setNombreEvento(request.getParameter("nombreEvento"));
                e.setSede(request.getParameter("sede"));
                e.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
                e.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));
                
                try {
                    dao.update(e);
                    response.sendRedirect("EventoServlet?accion=listaDeEventos");
                } catch (SQLException ex) {
                    Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
    }

    private void verEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EventoDAO dao = new EventoDAO();
        Evento e = new Evento();
        
        try {
            e.setIdEvento(Integer.parseInt(request.getParameter("id")));
            e = dao.read(e);
            
            
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Datos de evento</title>"); 
            out.println("<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class=\"jumbotron jumbotron-fluid\">");
                out.println("<div class=\"container\">");
                   out.println("<h1>ESCOM Eventos</h1>");
                out.println("</div>");
            out.println("</div>");
            
            out.println("<div class='container'>");
            
            
            out.println("<div class='card mb-3' style='max-width: 1200px;'>");
                out.println("<div class='row no-gutters'>");
                    out.println("<div class='col-md-4'>");
                        out.println("<img src='./IMAGENES/escom.jpg' class='card-img' alt='...'>");
                    out.println("</div>");
                    out.println("<div class='col-md-8'>");
                        out.println("<div class='card-body'>");
                            out.println("<h5 class='card-title'>ID de evento: "+ e.getIdEvento() +"</h5>");
                            out.println("<p class='card-text'>Nombre: "+ e.getNombreEvento() +"</p>");
                            out.println("<p class='card-text'>Sede: "+ e.getSede() +"</p>");
                            out.println("<p class='card-text'>Fecha de inicio: "+ e.getFechaInicio() +"</p>");
                            out.println("<p class='card-text'>Fecha de termino: "+ e.getFechaFin() +"</p>");
                            out.println("<p class='card-text'><small class='text-muted'>Información sujeta a cambio sin previo aviso.</small></p>");
                        out.println("</div>");
                    out.println("</div>");
                out.println("</div>");
            out.println("</div>");
            
            out.println("<a href='EventoServlet?accion=listaDeEventos' class='btn btn-outline-success'> <i class='bx bx-arrow-back'></i> Regresar </a>");
            
            out.println("</div>");            
            out.println("</body>");
            out.println("</html>");
            // tarea terminar fucnionalidad para actualizar un evento 
        }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EventoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
