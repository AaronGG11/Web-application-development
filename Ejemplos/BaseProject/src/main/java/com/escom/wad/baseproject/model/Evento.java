/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.baseproject.model;
import java.io.Serializable;
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
public class Evento implements Serializable
{
    private Integer idEvento;
    private String nombreEvento;
    private String sede;
    private Date fechaInicio;
    private Date fechaFin;
}