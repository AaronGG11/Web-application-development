/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dto;

import com.escom.wad.model.Categoria;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author aarongarcia
 */



@Data
@AllArgsConstructor

public class CategoriaDTO implements Serializable{
    // Data transfer object, representa a un modelo de dominio
    private Categoria entidad;
    
    public CategoriaDTO(){
        entidad = new Categoria();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("id categoria: ").append(entidad.getIdCategoria()).append("\n")
                .append("nombre categoria: ").append(entidad.getNombreCategoria()).append("\n")
                .append("descripcion categoria: ").append(entidad.getDescripcionCategoria()).append("\n");

        return sb.toString();
    }
    
    
    
}
