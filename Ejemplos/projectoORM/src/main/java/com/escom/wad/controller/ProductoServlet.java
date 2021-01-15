/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.controller;

import com.escom.wad.model.dao.CategoriaDAO;
import com.escom.wad.model.dao.ProductoDAO;
import com.escom.wad.model.dto.ProductoDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author aarongarcia
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {
    
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
            case "listaDeProductos":
                listarProductos(request, response);
                break;
            case "nuevo":
                agregarProducto(request, response);
                break;
            case "eliminar":
                eliminarProducto(request, response);
                break;
            case "actualizar":
                actualizarProducto(request, response);
                break;
            case "guardar":
                guardarProducto(request, response);
                break;
            case "ver":
                mostrarProducto(request, response);
                break;
            case "verpdf":
                verPDF(request, response);
                break;
            case "reporteProducto":
                reporteProducto(request, response);
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

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();

        try {
            List lista = dao.readAll();
            request.setAttribute("listaDeProductos", lista);
            RequestDispatcher vista = request.getRequestDispatcher("listaProductos.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        
        try {
            List lista = dao.readAll();
            request.setAttribute("listaDeCategorias", lista);
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("productoForm.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();

        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            listarProductos(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        CategoriaDAO cat_dao = new CategoriaDAO();
        
        
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        try {
            dto = dao.read(dto);
            request.setAttribute("dto", dto);
            
            List lista = cat_dao.readAll();
            request.setAttribute("listaDeCategorias", lista);
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("productoForm.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();

        // Atributos en común
        dto.getEntidad().setNombreProducto(request.getParameter("txtnombre"));
        dto.getEntidad().setDescripcionProducto(request.getParameter("txtdescripcion"));
        dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtprecio")));
        dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtexistencia")));
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtidcategoria")));

        try {
            if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) { // Nuevo elemento
                dao.create(dto);
                listarProductos(request, response);
            } else { // actualizacion
                dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
                dao.update(dto);
                listarProductos(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();

        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("verProducto.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            requestDispatcher.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            File plantilla_reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ReporteCategorias_Productos.jasper")); // directorio base de la app
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

    private void reporteProducto(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        Map parametros = new HashMap();
        parametros.put("ParIdProd", Integer.parseInt(request.getParameter("id")));
        
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            File plantilla_reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ProductoIndividual.jasper")); // directorio base de la app
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
