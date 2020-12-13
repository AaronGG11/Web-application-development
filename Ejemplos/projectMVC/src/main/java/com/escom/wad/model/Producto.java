/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author aarongarcia
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    private Integer idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Float precio;
    private Integer existencia;
    private Integer idCategoria;
}
