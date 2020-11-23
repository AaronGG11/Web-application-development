
import com.escom.wad.model.Categoria;
import com.escom.wad.model.dao.CategoriaDAO;
import com.escom.wad.model.dto.CategoriaDTO;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aarongarcia
 */
public class App {
    public static void main(String[] args) throws SQLException {
        CategoriaDTO dto = new CategoriaDTO();
        CategoriaDAO dao = new CategoriaDAO();
        
        Categoria entidad = new Categoria();
        entidad.setIdCategoria(1);
        entidad.setNombreCategoria("Bebes");
        entidad.setDescripcionCategoria("Ropa, juguetes");
        
        dto.setEntidad(entidad);
        
        System.out.println(dao.read(dto));
        
    }
}
