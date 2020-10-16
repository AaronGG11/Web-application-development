/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.baseproject.dao;
import com.escom.wad.baseproject.model.Evento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aarongarcia
 */
public class EventoDAO {
    private static final String SQL_INSERT = 
            "insert into Evento (nombreEvento, sede, fechaInicio, fechaFin) "
            + "values (?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = 
            "update Evento set "
            + "nombreEvento = ?, sede = ?, fechaIncio = ?, fechaFin = ? "
            + "where idEvento = ? ";
    
    private static final String SQL_DELETE = 
            "delete from Eventro where idEvento = ?";
    
    private static final String SQL_SELECT = 
            "select * from Eventro where idEvento = ?";
    
    private static final String SQL_SELECT_ALL = 
            "select * from Evento";
    
    private Connection connection;
    
    private void obtenerConexion()
    {
        String user = "root";
            String password = "rootroot";
            String url = "jdbc:mysql://localhost:3306/WAD?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String driverMySql = "com.mysql.cj.jdbc.Driver";
            
        try {
            Class.forName(driverMySql);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(Evento evento) throws SQLException
    {
        obtenerConexion();
        PreparedStatement ps = null;
        
        try {
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1, evento.getNombreEvento());
            ps.setString(2, evento.getSede());
            ps.setDate(3, evento.getFechaInicio());
            ps.setDate(4, evento.getFechaFin());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
    
    
    public void update(Evento evento) throws SQLException
    {
        obtenerConexion();
        PreparedStatement ps = null;
        
        try
        {
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, evento.getNombreEvento());
            ps.setString(2, evento.getSede());
            ps.setDate(3, evento.getFechaInicio());
            ps.setDate(4, evento.getFechaFin());
            ps.setInt(5, evento.getIdEvento());
            ps.executeUpdate();
        }
        finally
        {
            if (ps != null) {
                ps.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
    
    
    public void delete(Evento evento) throws SQLException
    {
        obtenerConexion();
        PreparedStatement ps = null;
        
        try
        {
            ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, evento.getIdEvento());
            ps.executeUpdate();
        }
        finally
        {
            if (ps != null) {
                ps.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
    
    
    public List readAll() throws SQLException
    {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = connection.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery(); // por ser de seleccion
            List resultados = obtenerResultados(rs);
            
            if(resultados.size() > 0)
            {
                return resultados;
            }
            else{
                return null;
            }
        } finally{
            if (rs != null) {
                rs.close();
            }
            if (ps != null){
                ps.close(); 
            }
            if(connection != null){
                connection.close();
            }
        }
    }
    
    
    public Evento read(Evento evento) throws SQLException
    {
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = connection.prepareStatement(SQL_SELECT);
            ps.setInt(1, evento.getIdEvento());
            rs = ps.executeQuery(); // por ser de seleccion
            List resultados = obtenerResultados(rs);
            
            if(resultados.size() > 0)
            {
                return (Evento)resultados.get(0);
            }
            else{
                return null;
            }
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();   
        }
    }
    
    
    private List obtenerResultados(ResultSet rs) throws SQLException 
    {
        List resultados = new ArrayList();
        
        while(rs.next())
        {
            Evento evento = new Evento();
            evento.setIdEvento(rs.getInt("idEvento"));
            evento.setNombreEvento(rs.getString("nombreEvento"));
            evento.setSede(rs.getString("sede"));
            evento.setFechaInicio(rs.getDate("fechaInicio"));
            evento.setFechaFin(rs.getDate("fechaFin"));
            
            resultados.add(evento);
        }
        
        return resultados;
    } 
}

