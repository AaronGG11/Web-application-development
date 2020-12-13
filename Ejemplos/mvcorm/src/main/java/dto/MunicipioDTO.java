/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import model.Municipio;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author aarongarcia
 */

@Data
@AllArgsConstructor

public class MunicipioDTO {
    private Municipio entidad;
    
    public MunicipioDTO(){
        entidad = new Municipio();
    }
}
