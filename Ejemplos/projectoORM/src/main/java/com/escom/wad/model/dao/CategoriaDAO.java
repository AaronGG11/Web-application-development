/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dao;

import com.escom.wad.model.Categoria;
import com.escom.wad.model.dto.CategoriaDTO;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author aarongarcia
 */
public class CategoriaDAO {
    // Define quries
    
    // By specification
    private static final String SQL_INSERT = "insert into categoria (nombrecategoria, descripcioncategoria) values(?,?)";
    private static final String SQL_UPDATE = "update categoria set nombrecategoria=?, descripcioncategoria=? where idcategoria=?";
    private static final String SQL_DELETE = "delete from categoria where idcategoria=?";
    private static final String SQL_SELECT = "select * from categoria where idcategoria=?";
    private static final String SQL_SELECT_ALL = "select * from categoria";
    
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
    
    public Connection getConnectionLocal(){ // mysql
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
        
        return connection;
    }
    
    // cntl al t c
    // alt shift abajo
    // Method definitions
    
    
    public void create(CategoriaDTO dto) throws SQLException{
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
    
    
    public void update(CategoriaDTO dto) throws SQLException{
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
    
    public void delete(CategoriaDTO dto) throws SQLException{
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
    
    public CategoriaDTO read(CategoriaDTO dto) throws SQLException{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        
        try{
            transaction.begin();
            
            dto.setEntidad(session.get(dto.getEntidad().getClass(), dto.getEntidad().getIdCategoria()));
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
            
            Query query = session.createQuery("from Categoria c order by c.idCategoria");
            for (Categoria c : (List<Categoria>)query.list()) {  
                CategoriaDTO dto = new CategoriaDTO();
                dto.setEntidad(c);
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
