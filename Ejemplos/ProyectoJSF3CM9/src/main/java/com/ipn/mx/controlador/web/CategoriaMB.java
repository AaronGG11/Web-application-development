/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author aarongarcia
 */

@Data
@Getter
@Setter

@ManagedBean(name = "categoriaMB") // en vez de enterprise
@SessionScoped
public class CategoriaMB extends BaseBean implements Serializable {

    private final CategoriaDAO dao = new CategoriaDAO();
    private CategoriaDTO dto;
    private List<CategoriaDTO> lista_categorias;

    /**
     * Creates a new instance of CategoriaMB
     */
    public CategoriaMB() {
    }

    // llenar listado de categorias
    @PostConstruct
    public void init() {
        lista_categorias = new ArrayList<>();
        lista_categorias = dao.readAll();
    }

    // todos los metodos del controlador regresan un recurso
    public String prepareAdd() {
        dto = new CategoriaDTO();
        setAccion(ACC_CREAR);

        return "/categoria/categoriaForm?faces-redirect=true";
    }

    public String prepareUpdate() {
        setAccion(ACC_ACTUALIZAR);

        return "/categoria/categoriaForm?faces-redirect=true";
    }

    public String prepareIndex() {
        init();
        return "/categoria/listadoCategorias?faces-redirect=true";
    }

    public String back() {
        return prepareIndex();
    }

    public boolean validate() {
        // Validar elementos que el usuario escribe en html
        boolean es_valido = true;

        // funcionad de validacion de manage bean
        /*
            Incluso usar anotaciones de la entidad
         */
        return es_valido;
    }

    public String add() {
        boolean es_valido = validate();

        if (es_valido) {
            dao.create(dto);
            /*
            if(es_valido){
                return prepareIndex();
            }else{
                return prepareAdd();
            }
             */
        }

        return prepareIndex();
    }

    public String update() {
        boolean es_valido = validate();

        if (es_valido) {
            dao.update(dto);
        }

        return prepareIndex();
    }

    public String delete() {
        dao.delete(dto);

        return prepareIndex();
    }

    public void seleccionarCategoria(ActionEvent event) {
        String clave_seleccionada = (String) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getRequestParameterMap().
                get("claveSel");

        dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(clave_seleccionada));

        try {
            dto = dao.read(dto);
        } catch (Exception e) {
            e.printStackTrace(); // change to logger
        }
    }

}
