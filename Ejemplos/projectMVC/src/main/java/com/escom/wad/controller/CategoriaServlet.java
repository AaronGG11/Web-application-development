/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.controller;

import com.escom.wad.model.dao.CategoriaDAO;
import com.escom.wad.model.dao.GraficaDAO;
import com.escom.wad.model.dto.CategoriaDTO;
import com.escom.wad.model.dto.GraficaDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author aarongarcia
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
public class CategoriaServlet extends HttpServlet {

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
            case "listaDeCategorias":
                listarCategorias(request, response);
                break;
            case "nuevo":
                agregarCategoria(request, response);
                break;
            case "eliminar":
                eliminarCategoria(request, response);
                break;
            case "actualizar":
                actualizarCategoria(request, response);
                break;
            case "guardar":
                guardarCategoria(request, response);
                break;
            case "ver":
                mostrarcategoria(request, response);
                break;
            case "graficar":
                graficar(request, response);
                break;
            case "verpdf":
                verPDF(request, response);
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

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();

        try {
            List lista = dao.readAll();
            request.setAttribute("listaDeCategorias", lista);
            RequestDispatcher vista = request.getRequestDispatcher("listaCategorias.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarcategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();

        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("verCategoria.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("categoria", dto);
            requestDispatcher.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("categoriaForm.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();

        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            listarCategorias(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Para actualizar y guardar
    private void guardarCategoria(HttpServletRequest request, HttpServletResponse response) {

        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();

        // Atributos en común
        dto.getEntidad().setNombreCategoria(request.getParameter("txtnombre"));
        dto.getEntidad().setDescripcionCategoria(request.getParameter("txtdescripcion"));

        try {
            if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) { // Nuevo elemento
                dao.create(dto);
                listarCategorias(request, response);
            } else { // actualizacion
                dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
                dao.update(dto);
                listarCategorias(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();

        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));

        try {
            dto = dao.read(dto);
            request.setAttribute("dto", dto);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("categoriaForm.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void graficar(HttpServletRequest request, HttpServletResponse response) {
        JFreeChart grafica = ChartFactory.createPieChart3D(
                "Productos por categoria", getGraficaProductos(), true, true, Locale.getDefault());
        
        // true #1 -> leyendas
       // true #2 -> tooltip
        
        String archivo = getServletConfig().getServletContext().getRealPath("/grafica.png"); // al momento de crearlo 
        try {
            ChartUtils.saveChartAsPNG(new File(archivo), grafica, 500, 500);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("grafica.jsp");
            requestDispatcher.forward(request, response);

        } catch (IOException | ServletException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PieDataset getGraficaProductos() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        GraficaDAO dao = new GraficaDAO();
        
        try {
            List datos = dao.grafica();
            for (int i = 0; i < datos.size(); i++) {
                GraficaDTO dto = (GraficaDTO)datos.get(i);
                pieDataset.setValue(dto.getNombre(), dto.getCantidad());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pieDataset;
    }

    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            File plantilla_reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ReporteCategorias_Productos.jasper")); // directorio base de la app
            byte[] bytes = JasperRunManager.runReportToPdf(plantilla_reporte.getPath(), null, dao.getConnection());
            
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
