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
    private static final String SQL_INSERT = "insert into usuario (nombre,paterno,materno,email,nombreUsuario,claveUsuario,tipoUsuario,imagen) values (?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update usuario set nombre=?, paterno=?, materno=?, email=?, nombreUsuario=?, claveUsuario=?, tipoUsuario=?, imagen=? where idUsuario=?";
    private static final String SQL_DELETE = "delete from usuario where idUsuario=?";
    private static final String SQL_SELECT = "select * from usuario where idUsuario=?";
    private static final String SQL_SELECT_ALL = "select * from usuario"; 
    private static final String SQL_FIND_BY_USERNAME_AND_PASSWORD = "select * from usuario where nombreUsuario=? and claveUsuario=?";
    
    private Connection connection;
    
    public Connection getConnection(){ // Pendiente a modificar
        String user = "tazufdqzewches";
            String password = "17a8624de0181e5380421d9cbce35d668dc13ac19670613f28fdc2a14558364a";
            String url = "jdbc:postgresql://ec2-54-157-66-140.compute-1.amazonaws.com:5432/d29b5simejk4r9?sslmode=require";
            String driverSql = "org.postgresql.Driver";
            
        try {
            Class.forName(driverSql);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
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
            callableStatement.setString(8, dto.getEntidad().getImagen());
            
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
            callableStatement.setString(8, dto.getEntidad().getImagen());
            callableStatement.setLong(9, dto.getEntidad().getIdUsuario());
            
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
            usuarioDTO.getEntidad().setImagen(resultSet.getString("imagen"));
            
            results.add(usuarioDTO);
        }
        
        return results;
    }
    
    
    
    
}
