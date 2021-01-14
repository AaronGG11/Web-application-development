
import com.escom.wad.model.dao.CategoriaDAO;
import com.escom.wad.model.dao.UsuarioDAO;
import com.escom.wad.utileria.EnviarMails;
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
        //CategoriaDAO dao = new CategoriaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        System.out.println(usuarioDAO.readAll());
    }
}
