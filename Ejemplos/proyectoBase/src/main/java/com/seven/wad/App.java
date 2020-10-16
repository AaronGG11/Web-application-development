/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seven.wad;

import com.seven.wad.dao.EventoDAO;
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
public class App
{
    public static void main(String[] args) throws SQLException 
    {
        Evento evento = new Evento();
        evento.setIdEvento(3);
        evento.setNombreEvento("Semana de educación sexual");
        evento.setSede("ESIME");
        
        EventoDAO dao = new EventoDAO();
        System.out.println(dao.readAll());
    }
}
