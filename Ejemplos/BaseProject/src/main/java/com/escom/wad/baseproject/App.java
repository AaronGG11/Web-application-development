/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.baseproject;

import com.escom.wad.baseproject.dao.EventoDAO;
import com.escom.wad.baseproject.model.Evento;
import java.sql.SQLException;

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
       //dao.create(evento);
    }
}
