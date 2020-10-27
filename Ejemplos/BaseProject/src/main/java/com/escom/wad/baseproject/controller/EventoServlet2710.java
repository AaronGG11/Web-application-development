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
public class EventoServlet2710 extends HttpServlet {

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
                            guardarEvento(request, response);
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
            out.println("<Link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>");
            out.println("<title>Lista de eventos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 class='h1'>Lista de eventos</h1>");
            
            
            out.println("<table class='table'>"); 
            out.println("<thead>");
            out.println("<tr>");
            out.print("<th scope='col'>id</th>");
            out.print("<th scope='col'>Nombre</th>");
            out.print("<th scope='col'>Sede</th>");
            out.print("<th scope='col'>Fecha Inicio</th>");
            out.print("<th scope='col'>Fecha Fin</th>");
            out.println("</tr>");
            out.println("</thead>");
            
            
            // tabla
            int idEvento;
            String nombreEvento;
            String sede;
            Date fechaInicio;
            Date fechaFin;
            
            
            EventoDAO dao = new EventoDAO();
            
            try {
                List lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    Evento e = (Evento)lista.get(i);
                    idEvento = e.getIdEvento();
                    nombreEvento = e.getNombreEvento();
                    sede = e.getSede();
                    fechaInicio = e.getFechaInicio();
                    fechaFin = e.getFechaFin();
                    
                    out.println("<tr>");
                    out.println("<td>"+ idEvento +"</td>");
                    out.println("<td>"+ nombreEvento +"</td>");
                    out.println("<td>"+ sede +"</td>");
                    out.println("<td>"+ fechaInicio +"</td>");
                    out.println("<td>"+ fechaFin+"</td>");
                    out.println("<td>"+ "Eliminar | Actualizar" + "</td>");
                    out.println("</tr>");
                              
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(EventoServlet2710.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("</table>"); 
            
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void nuevoEvento(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void eliminarEvento(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void actualizarEvento(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void guardarEvento(HttpServletRequest request, HttpServletResponse response) {
        String nombreEvento = "request.getParameter('nombreEvento')"; // hacerlo con el index
        
        Evento e = new Evento();
        e.setNombreEvento(nombreEvento);
        e.setSede(request.getParameter("sede"));
        e.setFechaInicio(Date.valueOf(request.getParameter("fechaInicio")));
        e.setFechaFin(Date.valueOf(request.getParameter("fechaFin")));
        
        EventoDAO dao = new EventoDAO();
        
        try {
            dao.create(e);
            // redirigir a alguna pagina para que muestre algo 
            
        } catch (SQLException ex) {
            Logger.getLogger(EventoServlet2710.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void verEvento(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
