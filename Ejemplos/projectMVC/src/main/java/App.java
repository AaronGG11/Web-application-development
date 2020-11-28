
import com.escom.wad.model.Categoria;
import com.escom.wad.model.EntidadFederativa;
import com.escom.wad.model.dao.CategoriaDAO;
import com.escom.wad.model.dao.EntidadFederativaDAO;
import com.escom.wad.model.dto.CategoriaDTO;
import com.escom.wad.model.dto.EntidadFederativaDTO;
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
        // Llenar tabla de entidad federativa
        EntidadFederativaDAO dao = new EntidadFederativaDAO();
        EntidadFederativaDTO dto = new EntidadFederativaDTO();
        
        
        
        // Leer banco de datos
            
        dao.create(dto);    
        
            
        // Llenar tabla de municipios
            // Leer banco de datos
        
        
    }
}
