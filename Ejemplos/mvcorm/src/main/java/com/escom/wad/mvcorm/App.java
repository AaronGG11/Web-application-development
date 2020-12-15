/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.mvcorm;

import dao.UsuarioDAO;
import dto.UsuarioDTO;

/**
 *
 * @author aarongarcia
 */
public class App {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        
        dto.getEntidad().setIdUsuario(1);
        dto.getEntidad().setNombre("Aaron");
        dto.getEntidad().setMaterno("Garcia");
        dto.getEntidad().setPaterno("Gonzalez");
        dto.getEntidad().setEmail("algo.com");
        dto.getEntidad().setClaveUsuario("gvfcd");
        dto.getEntidad().setNombreUsuario("dffdf");
        dto.getEntidad().setTipoUsuario("gfcc");
        
        dao.create(dto);
    }
}
