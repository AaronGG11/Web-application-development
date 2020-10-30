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
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class=\"jumbotron jumbotron-fluid\">");
                out.println("<div class=\"container\">");
                   out.println("<h1>Lista de eventos</h1>");
                out.println("</div>");
            out.println("</div>");
            
            
            
            out.println("<div class='container'>");
            
            out.println("<a href='EventoServlet?accion=nuevo' class='btn btn-success'>Nuevo Evento</a>");
            

            out.println("<table class='table table-stripped table-responsive table-bordered'>");

            out.println("<tr>");
            
            out.println("<thead class='thead-dark'>");
            
            out.println("<th>");
            out.println("Clave de Evento");
            out.println("</th>");

            out.println("<th>");
            out.println("Nombre del Evento");
            out.println("</th>");

            out.println("<th>");
            out.println("Sede");
            out.println("</th>");

            out.println("<th>");
            out.println("Fecha de Inicio");
            out.println("</th>");

            out.println("<th>");
            out.println("Fecha de Término");
            out.println("</th>");

            out.println("<th>");
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
                    out.println("<td>"+idEvento+"</td>");
                    out.println("<td>"+nombreEvento+"</td>");
                    out.println("<td>"+sede+"</td>");
                    out.println("<td>"+fechaInicio+"</td>");
                    out.println("<td>"+fechaTermino+"</td>");
                    
                    out.println("<td>");
                    // Ocultar mediante un formulario para no ver id, si no quiero mostrar nada
                    out.println("<a href='EventoServlet?accion=eliminar&id=" + idEvento +"' > Eliminar </a>");
      
                    out.println("<a href='EventoServlet?accion=actualizar&id=" + idEvento +"' > Actualizar </a>");
                    out.println("<a href='EventoServlet?accion=ver&id=" + idEvento +"' > Ver </a>");
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

    private void actualizarEvento(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            out.println("<title>Lista de eventos</title>"); 
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class=\"jumbotron jumbotron-fluid\">");
                out.println("<div class=\"container\">");
                   out.println("<h1>Datos del evento</h1>");
                out.println("</div>");
            out.println("</div>");
            
            out.println("<div class='container'>");
            out.println("<div class=\"card text-white bg-primary mb-3\" style=\"max-width: 18rem;\">");
            out.println("<div class=\"card-header\">ID Evento: " + e.getIdEvento() + "</div>");
            out.println("<div class=\"card-body\">");
            
            out.println("<p>Nombre del evento: "+ e.getNombreEvento()+"</p>");
            out.println("<p>Sede del evento: "+ e.getSede() +"</p>");
            out.println("<p>Fecha de inicio del evento: "+ e.getFechaInicio() +"</p>");
            out.println("<p>Fecha de fin del evento: "+ e.getFechaFin() +"</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
         
            // regresar a lista
            //out.printl("")

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
