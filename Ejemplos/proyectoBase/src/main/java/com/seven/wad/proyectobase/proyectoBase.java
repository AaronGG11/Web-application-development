/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seven.wad.proyectobase;

import com.seven.wad.model.Evento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aarongarcia
 */
public class proyectoBase
{
    public static void main(String[] args) 
    {
        Evento evento = new Evento();
        evento.setIdEvento(1);
        evento.setNombreEvento("Semana cultural");
        evento.setSede("ESCOM");
        
        String user = "root";
        String password = "rootroot";
        String url = "jdbc:mysql://localhost:3306/WAD?serverTimezone=America/Mexico_City&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false";
        String driverMySql = "com.mysql.cj.jdbc.Driver";
        
        Connection connection = null;
        try {
            Class.forName(driverMySql);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(proyectoBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(proyectoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //actualizar(connection, evento);
        //eliminar(connection, evento);
        //consultar(connection, evento);
        insertar(connection, evento);   
    }

    private static void actualizar(Connection connection, Evento evento) {
        PreparedStatement ps = null;
        String SQL_UPDATE = "update Evento set nombreEvento = ?, sede = ?, fechaInicio = ?, fechaFin = ? where idEvento = ?";
        
        try {
            ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, evento.getNombreEvento());
            ps.setString(2, evento.getSede());
            ps.setDate(3, evento.getFechaInicio());
            ps.setDate(4, evento.getFechaFin());
            ps.setInt(5, evento.getIdEvento());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(proyectoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void eliminar(Connection connection, Evento evento) {
        PreparedStatement ps = null;
        String SQL_DELETE = "delete from Evento where idEvento = ?";
        
        try {
            ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, evento.getIdEvento());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(proyectoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void consultar(Connection connection, Evento evento) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String SQL_SELECT = "select * from Evento where idEvento = ?";
        try {
            ps = connection.prepareStatement(SQL_SELECT);
            ps.setInt(1, evento.getIdEvento());
            rs = ps.executeQuery();
            
            Evento test = new Evento();
            while(rs.next())
            {
                evento.setIdEvento(rs.getInt("idEvento")); // o indice de col
                evento.setNombreEvento(rs.getString("nombreEvento"));
                evento.setSede(rs.getString("sede"));
                evento.setFechaInicio(rs.getDate("fechaInicio"));
                evento.setFechaFin(rs.getDate("fechaFin"));
                System.out.println(evento.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(proyectoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void insertar(Connection connection, Evento evento) {
        PreparedStatement ps = null;
        String SQL_INSERT = "insert into Evento(nombreEvento, sede, fechaInicio, fechaFin) values(?,?,?,?)";
        
        try {
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1, evento.getNombreEvento());
            ps.setString(2, evento.getSede());
            ps.setDate(3, evento.getFechaInicio());
            ps.setDate(4, evento.getFechaFin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(proyectoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
