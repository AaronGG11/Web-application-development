/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

@Entity 
@Table(name = "Usuario", schema = "public")

public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    
    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;
    
    @Column(name = "paterno")
    private String paterno;
    
    @Column(name = "materno")
    private String materno;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    
    @Column(name = "claveUsuario")
    private String claveUsuario;
    
    @Column(name = "tipoUsuario")
    private String tipoUsuario; 
}
