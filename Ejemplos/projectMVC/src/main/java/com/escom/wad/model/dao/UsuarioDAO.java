/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dao;

import com.escom.wad.model.dto.CategoriaDTO;
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
public class UsuarioDAO {
    private static final String SQL_INSERT = "{call spInsertarUsuario(?,?,?,?,?,?,?)}";
    private static final String SQL_UPDATE = "{call spActualizarUsuario(?,?,?,?,?,?,?,?)}";
    private static final String SQL_DELETE = "{call spBorrarUsuario(?)}";
    private static final String SQL_SELECT = "{call spVerUsuario(?)}";
    private static final String SQL_SELECT_ALL = "{call spMostrarUsuarios()}"; 
    private static final String SQL_FIND_BY_USERNAME_AND_PASSWORD = "{call spLogin(?,?)}";
    
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
    
    public void create(UsuarioDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_INSERT);
            callableStatement.setString(1, dto.getEntidad().getNombre());
            callableStatement.setString(2, dto.getEntidad().getPaterno());
            callableStatement.setString(3, dto.getEntidad().getMaterno());
            callableStatement.setString(4, dto.getEntidad().getEmail());
            callableStatement.setString(5, dto.getEntidad().getNombreUsuario());
            callableStatement.setString(6, dto.getEntidad().getClaveUsuario());
            callableStatement.setString(7, dto.getEntidad().getTipoUsuario());
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
    
    public void update(UsuarioDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_UPDATE);
            callableStatement.setString(1, dto.getEntidad().getNombre());
            callableStatement.setString(2, dto.getEntidad().getPaterno());
            callableStatement.setString(3, dto.getEntidad().getMaterno());
            callableStatement.setString(4, dto.getEntidad().getEmail());
            callableStatement.setString(5, dto.getEntidad().getNombreUsuario());
            callableStatement.setString(6, dto.getEntidad().getClaveUsuario());
            callableStatement.setString(7, dto.getEntidad().getTipoUsuario());
            callableStatement.setLong(8, dto.getEntidad().getIdUsuario());
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
    
    public void delete(UsuarioDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_DELETE);
            callableStatement.setLong(1, dto.getEntidad().getIdUsuario());
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
    
    
    public UsuarioDTO read(UsuarioDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_SELECT);
            callableStatement.setLong(1, dto.getEntidad().getIdUsuario());
            
            resultSet = callableStatement.executeQuery();
            List lista = obtenerResultados(resultSet);
            if(lista.size() > 0){
                return (UsuarioDTO) lista.get(0);
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
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.getEntidad().setIdUsuario(resultSet.getInt("idUsuario"));
            usuarioDTO.getEntidad().setNombre(resultSet.getString("nombre"));
            usuarioDTO.getEntidad().setPaterno(resultSet.getString("paterno"));
            usuarioDTO.getEntidad().setMaterno(resultSet.getString("materno"));
            usuarioDTO.getEntidad().setEmail(resultSet.getString("email"));
            usuarioDTO.getEntidad().setNombreUsuario(resultSet.getString("nombreUsuario"));
            usuarioDTO.getEntidad().setClaveUsuario(resultSet.getString("claveUsuario"));
            usuarioDTO.getEntidad().setTipoUsuario(resultSet.getString("tipoUsuario"));    
        }
        
        return results;
    }
    
    
    
    
}
