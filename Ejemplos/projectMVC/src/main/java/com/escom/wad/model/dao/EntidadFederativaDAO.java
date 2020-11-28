/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dao;

import com.escom.wad.model.dto.EntidadFederativaDTO;
import com.escom.wad.model.dto.UsuarioDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class EntidadFederativaDAO {
    private static final String SQL_INSERT = "{call spInsertarEntidadFederativa(?,?)}";
    private static final String SQL_DELETE = "{call spBorrarEntidadFederativa(?)}";
    private static final String SQL_SELECT = "{call spVerEntidadFederativa(?)}";
    private static final String SQL_SELECT_ALL = "{call spVerEntidadesFederativas()}";
    
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
    
    
    public void create(EntidadFederativaDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_INSERT);
            callableStatement.setString(1, dto.getEntidad().getNombre());
            callableStatement.setString(2, dto.getEntidad().getAbreviatura());

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
    
    
    public void delete(EntidadFederativaDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_DELETE);
            callableStatement.setLong(1, dto.getEntidad().getIdEntidadFederativa());
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
    
    
    public EntidadFederativaDTO read(EntidadFederativaDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_SELECT);
            callableStatement.setLong(1, dto.getEntidad().getIdEntidadFederativa());
            
            resultSet = callableStatement.executeQuery();
            List lista = obtenerResultados(resultSet);
            if(lista.size() > 0){
                return (EntidadFederativaDTO) lista.get(0);
            }else{
                return null;
            }
        } finally  {
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
        ResultSet resultSet = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_SELECT_ALL);
            resultSet = callableStatement.executeQuery();
            
            List results = obtenerResultados(resultSet);
            
            if(results.size() > 0){
                return results;
            }else{
                return null;
            }
            
            
        } finally  {
            if(callableStatement != null){
                callableStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }
    
    
    private List obtenerResultados(ResultSet resultSet) throws SQLException {
        List results = new ArrayList();
        
        while(resultSet.next()){
            EntidadFederativaDTO entidadFederativaDTO = new EntidadFederativaDTO();
            entidadFederativaDTO.getEntidad().setIdEntidadFederativa(resultSet.getInt("idEntidadFederativa"));
            entidadFederativaDTO.getEntidad().setNombre(resultSet.getString("nombre"));
            entidadFederativaDTO.getEntidad().setAbreviatura(resultSet.getString("abreviatura"));
        }
        
        return results;
    }
    
    
    
    
    
}
