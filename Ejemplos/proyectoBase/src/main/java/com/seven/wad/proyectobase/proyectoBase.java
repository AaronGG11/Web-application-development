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
        
        String user = "roor";
        String password = "rootroot";
        String url = "jdbc::mysql://localhost::3306/Eventos";
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
        
        PreparedStatement ps = null;
        String SQL_INSERT = "insert into Evento(nombreEvento, sede, fechaIncio, fechaFin) values(?,?,?,?)";
        
        try {
            ps = connection.prepareStatement(SQL_INSERT);
            ps.setString(1, evento.getNombreEvento());
            ps.setString(2, evento.getSede());
            ps.setDate(3, evento.getFechaIncio());
            ps.setDate(4, evento.getFechaFin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(proyectoBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
