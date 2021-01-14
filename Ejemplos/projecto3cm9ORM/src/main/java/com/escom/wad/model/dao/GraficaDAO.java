/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dao;

import com.escom.wad.model.dto.GraficaDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class GraficaDAO {

    private static final String SQL_GRAFICAR
            = "select c.nombreCategoria as Categoria, count(*) as Productos "
            + "from Productos p, Categoria c where p.idCategoria = c.idCategoria"
            + "group by c.idCategoria"; // verificar para postgres

    private Connection connection;

    private void getConnection() {
        String user = "xnqkjayyajynlf";
        String password = "b4aa424ae3aff39acc4292ed6722c991eea4b9c604064aa7a74d9b51f69f1210";
        String url = "jdbc:postgresql://ec2-23-22-156-110.compute-1.amazonaws.com:5432/d5efdn4q82rse3?sslmode=require";
        String driverSql = "org.postgresql.Driver";

        try {
            Class.forName(driverSql);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List grafica() throws SQLException {
        getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List lista = new ArrayList();

        try {
            preparedStatement = connection.prepareStatement(SQL_GRAFICAR);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(resultSet.getString("Categoria"));
                dto.setCantidad(resultSet.getInt("Productos"));
                lista.add(dto);
            }
            
            
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return lista;
    }
}
