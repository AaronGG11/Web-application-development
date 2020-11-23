/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dao;

import com.escom.wad.model.dto.CategoriaDTO;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author aarongarcia
 */
public class CategoriaDAO {
    // Define quries
    
    // By specification
    private static final String SQL_INSERT = "{call spInsertarCategoria(?,?)}";
    private static final String SQL_UPDATE = "{call spActualizarCategoria(?,?,?)}";
    private static final String SQL_DELETE = "{call spBorrarCategoria(?)}";
    private static final String SQL_SELECT = "{call spVerCategoria(?)}";
    private static final String SQL_SELECT_ALL = "{call spMostrarCategorias()}";
    
    private Connection connection;
    
    private void getConnection(){
        String user = "root";
            String password = "rootroot";
            String url = "jdbc:mysql://localhost:3306/WAD?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String driverMySql = "com.mysql.cj.jdbc.Driver";
            
        try {
            Class.forName(driverMySql);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // alt shift abajo
    // Method definitions
    
    
    public void create(CategoriaDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_INSERT);
            callableStatement.setString(1, dto.getEntidad().getNombreCategoria());
            callableStatement.setString(2, dto.getEntidad().getDescripcionCategoria());
            callableStatement.executeUpdate(); // review
        } finally  {
            if(callableStatement != null){
                callableStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }
    
    
    public void update(CategoriaDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_UPDATE);
            callableStatement.setString(1, dto.getEntidad().getNombreCategoria());
            callableStatement.setString(2, dto.getEntidad().getDescripcionCategoria());
            callableStatement.setInt(3, dto.getEntidad().getIdCategoria());
            
            callableStatement.executeUpdate();
        } finally  {
            if(callableStatement != null){
                callableStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }
    
    public void delete(CategoriaDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_DELETE);
            callableStatement.setInt(1, dto.getEntidad().getIdCategoria());
            callableStatement.executeUpdate();
        } finally  {
            if(callableStatement != null){
                callableStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }
    
    public CategoriaDTO read(CategoriaDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        ResultSet rs = null;
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_SELECT);
            callableStatement.setInt(1, dto.getEntidad().getIdCategoria());
            
            rs = callableStatement.executeQuery();
            
            List results = getResults(rs);
            
            if(results.size() > 0){
                return (CategoriaDTO) results.get(0);
            }else{
                return null;
            }
            
        } finally  {
            if(rs != null){
                rs.close();
            }
            if(callableStatement != null){
                callableStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        
    }
    
    
    public List readAll() throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        ResultSet rs = null;
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_SELECT_ALL);
            rs = callableStatement.executeQuery();
            
            List results = getResults(rs);
            
            if(results.size() > 0){
                return results;
            }else{
                return null;
            }
            
        } finally  {
            if(rs != null){
                rs.close();
            }
            if(callableStatement != null){
                callableStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        
    }
    
    
    private List getResults(ResultSet rs) throws SQLException{
        List results = new ArrayList();
        
        while(rs.next()){
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            categoriaDTO.getEntidad().setNombreCategoria(rs.getString("nombreCategoria"));
            categoriaDTO.getEntidad().setDescripcionCategoria(rs.getString("descripcionCategoria"));
            
            results.add(categoriaDTO);
        }
        
        return results;
    }
    
    
}
