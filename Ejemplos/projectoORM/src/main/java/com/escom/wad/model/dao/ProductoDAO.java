/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dao;

import com.escom.wad.model.dto.ProductoDTO;
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
public class ProductoDAO {
    private static final String SQL_INSERT = "insert into producto (nombreProducto, descripcionProducto, precio, existencia, idCategoria) values (?,?,?,?,?)";
    private static final String SQL_UPDATE = "update producto set nombreProducto=?, descripcionProducto=?, precio=?, existencia=?, idCategoria=? where idproducto=?";
    private static final String SQL_DELETE = "delete from producto where idproducto=?";
    private static final String SQL_SELECT = "select * from producto where idproducto=?";
    private static final String SQL_SELECT_ALL = "select * from producto";
    
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
    
    public void create(ProductoDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_INSERT);
            callableStatement.setString(1, dto.getEntidad().getNombreProducto());
            callableStatement.setString(2, dto.getEntidad().getDescripcionProducto());
            callableStatement.setFloat(3, dto.getEntidad().getPrecio());
            callableStatement.setInt(4, dto.getEntidad().getExistencia());
            callableStatement.setInt(5, dto.getEntidad().getIdCategoria());

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
    
    public void update(ProductoDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
            
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_UPDATE);
            callableStatement.setString(1, dto.getEntidad().getNombreProducto());
            callableStatement.setString(2, dto.getEntidad().getDescripcionProducto());
            callableStatement.setFloat(3, dto.getEntidad().getPrecio());
            callableStatement.setInt(4, dto.getEntidad().getExistencia());
            callableStatement.setInt(5, dto.getEntidad().getIdCategoria());
            callableStatement.setInt(0, dto.getEntidad().getIdProducto());
            
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
    
    
    public void delete(ProductoDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_DELETE);
            callableStatement.setInt(1, dto.getEntidad().getIdProducto());
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
    
    
    public ProductoDTO read(ProductoDTO dto) throws SQLException{
        getConnection();
        CallableStatement callableStatement = null;
        
        ResultSet rs = null;
        try {
            callableStatement = (CallableStatement) connection.prepareCall(SQL_SELECT);
            callableStatement.setInt(1, dto.getEntidad().getIdProducto());
            
            rs = callableStatement.executeQuery();
            
            List results = getResults(rs);
            
            if(results.size() > 0){
                return (ProductoDTO) results.get(0);
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
            ProductoDTO productoDTO = new ProductoDTO();
            
            productoDTO.getEntidad().setIdProducto(rs.getInt("idProducto"));
            productoDTO.getEntidad().setNombreProducto(rs.getString("nombreProducto"));
            productoDTO.getEntidad().setDescripcionProducto(rs.getString("descripcionProducto"));
            productoDTO.getEntidad().setPrecio(rs.getFloat("precio"));
            productoDTO.getEntidad().setExistencia(rs.getInt("existencia"));
            productoDTO.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            
            results.add(productoDTO);
        }
        
        return results;
    }
    
}
