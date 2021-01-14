/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.wad.utileria;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author aarongarcia
 */
public class EnviarMails {
    public void enviarCorreo(String destinatario, String asunto, String mensaje){
        try {
            // Propiedades de la conexion
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.user", "aaron.eng99@gmail.com");
            properties.setProperty("mail.smtp.auth", "true");
            
            // Crear la parte de la sesion
            Session session = Session.getDefaultInstance(properties);
            
            // Redactar mensaje
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mimeMessage.setSubject(asunto);
            mimeMessage.setText(mensaje);
            
            Transport transport = session.getTransport("smtp"); //
            transport.connect(properties.getProperty("mail.smtp.user"), "algo gg");
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            
            // por defecto gmail no esta habilitada para aplicaciones por terceros, hay que hacerlo
            // https://myaccount.google.com/lesssecureapps
            
            
        } catch (AddressException ex) {
            Logger.getLogger(EnviarMails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarMails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
