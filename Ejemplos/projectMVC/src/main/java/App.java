
import com.escom.wad.utileria.EnviarMails;

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
    public static void main(String[] args) {
        EnviarMails email = new EnviarMails();
        
        String destinatario = "cetisofimatica4.10@gmail.com";
        String asunto = "Registro exitoso";
        String mensaje = "cdce";
        
        email.enviarCorreo(destinatario, asunto, mensaje);
    }
}
