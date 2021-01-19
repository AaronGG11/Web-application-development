/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Categoria;
import com.ipn.mx.modelo.entidades.Producto;
import java.io.Serializable;

/**
 *
 * @author aarongarcia
 */
public class CategoriaDTO implements Serializable{
    private Categoria entidad;

    public CategoriaDTO() {
        entidad = new Categoria();
    }

    public CategoriaDTO(Categoria entidad) {
        this.entidad = entidad;
    }

    public Categoria getEntidad() {
        return entidad;
    }

    public void setEntidad(Categoria entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id Categoría : ").append(getEntidad().getIdCategoria()).append("\n");
        sb.append("Nombre Categoría : ").append(getEntidad().getNombreCategoria()).append("\n");
        sb.append("Descripción Categoría : ").append(getEntidad().getDescripcionCategoria()).append("\n");
        
        sb.append("*******************************************************************************").append("\n");
        sb.append(" Datos de Productos ").append("\n");
        sb.append("*******************************************************************************").append("\n");
        for (Producto p : getEntidad().getListaProducto()) {
            
            sb.append("Clave Producto ===> : ").append(p.getIdProducto()).append("\n");
            sb.append("Nombre Producto ===> : ").append(p.getNombreProducto()).append("\n");
            sb.append("Descripción Producto ===> : ").append(p.getDescripcionProducto()).append("\n");
            sb.append("Existencia Producto ===> : ").append(p.getExistencia()).append("\n");
            sb.append("Precio Producto ===> : ").append(p.getPrecio()).append("\n");
            sb.append("Clave Categoria ===> : ").append(p.getIdCategoria().getIdCategoria()).append("\n");
        }
        
        return sb.toString();
    }
    
    
}
