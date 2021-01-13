/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import lombok.Data;

/**
 *
 * @author aarongarcia
 */

@Data
public class BaseBean {
    protected static final String ACC_CREAR = "CREAR";
    protected static final String ACC_ACTUALIZAR = "ACTUALIZAR";
    protected String accion;
    
    public boolean isModoCrear(){
        if(accion != null){
            return accion.equals(ACC_CREAR);
        }else{
            return false;
        }
    }
    
    public boolean isModoActualizar(){
        if(accion != null){
            return accion.equals(ACC_ACTUALIZAR);
        }else{
            return false;
        }
    }
    
    
}
