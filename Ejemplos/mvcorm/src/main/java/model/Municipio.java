/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author aarongarcia
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Municipio implements Serializable{
    private Integer idMunicipio;
    private Integer idEntidadFederativa;
    private String nombre;
}
