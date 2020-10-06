/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seven.wad.model;
import java.sql.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

/**
 * @author aarongarcia
 */
public class Evento 
{
    private Integer idEvento;
    private String nombreEvento;
    private String sede;
    private Date fechaIncio;
    private Date fechaFin;
}
