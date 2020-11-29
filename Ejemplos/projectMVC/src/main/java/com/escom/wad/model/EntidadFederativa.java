/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author aarongarcia
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EntidadFederativa implements Serializable{
    private Integer idEntidadFederativa;
    private String abreviatura;
    private String nombre;
}
