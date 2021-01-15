/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model.dto;

import com.escom.wad.model.Producto;
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

public class ProductoDTO {
    private Producto entidad;
    
    public ProductoDTO(){
        entidad = new Producto();
    }
}
