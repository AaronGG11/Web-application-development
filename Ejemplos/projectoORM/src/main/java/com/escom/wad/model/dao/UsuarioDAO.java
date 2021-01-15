/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dao;

import com.escom.wad.model.Categoria;
import com.escom.wad.model.Usuario;
import com.escom.wad.model.dto.CategoriaDTO;
import com.escom.wad.model.dto.UsuarioDTO;
import com.escom.wad.utileria.HibernateUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            session.save(dto.getEntidad());
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
    }
    
    public void update(UsuarioDTO dto) throws SQLException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            session.update(dto.getEntidad());
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
    }
    
    public void delete(UsuarioDTO dto) throws SQLException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            session.delete(dto.getEntidad());
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
    }
    
    
    public UsuarioDTO read(UsuarioDTO dto) throws SQLException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdUsuario()));
            dto.setEntidad(dto.getEntidad());
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
        
        return dto;
    }
    
    public List readAll() throws SQLException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List lista = new ArrayList();
        
        try{
            transaction.begin();
            
            Query query = session.createQuery("from Usuario u order by u.idUsuario");
            for (Usuario u : (List<Usuario>)query.list()) {  
                UsuarioDTO dto = new UsuarioDTO();
                dto.setEntidad(u);
                lista.add(dto);
            }
            
            transaction.commit();
        }catch(HibernateException hibernateException){
            if(transaction != null && transaction.isActive()){
                transaction.rollback(); // haces todo o haces nada 
            }
        }finally{ // liberar recursos
            
        }
        
        return lista;
    }

}
