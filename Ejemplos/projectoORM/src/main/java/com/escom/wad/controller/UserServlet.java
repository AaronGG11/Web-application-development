/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.controller;

import com.escom.wad.model.dao.CategoriaDAO;
import com.escom.wad.model.dao.UsuarioDAO;
import com.escom.wad.model.dto.UsuarioDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author aarongarcia
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 5, 
  maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserServlet extends HttpServlet {
    
    private JRBeanCollectionDataSource dataSource;
    
    public JRBeanCollectionDataSource getDataSource() {
	return dataSource;
    }

    public void setDataSource(JRBeanCollectionDataSource dataSource) {
		this.dataSource = dataSource;
    }

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
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "listaDeUsuarios":
                listarUsuarios(request, response);
                break;
            case "nuevo":
                agregarUsuario(request, response);
                break;
            case "eliminar":
                eliminarUsuario(request, response);
                break;
            case "actualizar":
                actualizarUsuario(request, response);
                break;
            case "guardar":
                guardarUsuario(request, response);
                break;
            case "ver":
                mostrarUsuario(request, response);
                break;
            case "verpdf":
                verPDF(request, response);
                break;
            case "reporteUsuario":
                reporteUsuario(request, response);
                break;
            default:
                break;
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

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();

        try {
            List lista = dao.readAll();
            request.setAttribute("listaDeUsuarios", lista);
            RequestDispatcher vista = request.getRequestDispatcher("listaUsuarios.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mostrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();

        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("verUsuario.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);
            requestDispatcher.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("usuarioForm.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();

        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            listarUsuarios(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();

        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        try {
            dto = dao.read(dto);
            request.setAttribute("dto", dto);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("usuarioForm.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();

        // Atributos en común
        dto.getEntidad().setNombre(request.getParameter("txtnombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtpaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtmaterno"));
        dto.getEntidad().setEmail(request.getParameter("txtemail"));
        dto.getEntidad().setNombreUsuario(request.getParameter("txtnombreUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtclaveUsuario"));
        dto.getEntidad().setTipoUsuario(request.getParameter("txttipoUsuario"));
        dto.getEntidad().setImagen(request.getParameter("txtimagen"));
        
        
        try {
            Part archivo = request.getPart("imagen"); //llamada al parámetro foto de mi formulario.
            
            String path_carpeta = request.getServletContext().getRealPath("imagenes_usuario"); //img es la carpeta que he creado en mi proyecto, dentro de la carpeta Web Content.
            String imagen_nombre = Paths.get(archivo.getSubmittedFileName()).getFileName().toString(); 
            
            dto.getEntidad().setImagen(imagen_nombre);
            archivo.write(path_carpeta + File.separator + imagen_nombre);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try { 
            if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) { // Nuevo elemento
                dao.create(dto);
                listarUsuarios(request, response);
            } else { // actualizacion
                dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
                dao.update(dto);
                listarUsuarios(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Reporte pdf con todos los usuarios
    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            File plantilla_reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ReporteUsuarios.jasper")); // directorio base de la app
            byte[] bytes = JasperRunManager.runReportToPdf(plantilla_reporte.getPath(), null, dataSource);
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            
            servletOutputStream.write(bytes,0,bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void reporteUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        Map parametros = new HashMap();
        parametros.put("ParIDUsr", Integer.parseInt(request.getParameter("id")));
        
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            File plantilla_reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/UsuarioIndividual.jasper")); // directorio base de la app
            byte[] bytes = JasperRunManager.runReportToPdf(plantilla_reporte.getPath(), parametros, dataSource);
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            
            servletOutputStream.write(bytes,0,bytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException | JRException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
