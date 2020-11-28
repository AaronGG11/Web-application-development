/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dto;

import com.escom.wad.model.Usuario;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author aarongarcia
 */

@Data
@AllArgsConstructor

public class UsuarioDTO implements Serializable{
    private Usuario entidad;
    
    public UsuarioDTO(){
        entidad = new Usuario();
    }
}
