package    utils;

import Entités.Utilisateur;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoyerEmail {

    private String username = "russia2018pidev@gmail.com";
    private String password = "russia2018";

    public void EnvoyerMail (List<Utilisateur> ls, String a, String b, String c, String d, String e) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
         for (Utilisateur u : ls) {
        try {
            System.out.println(u.getEmail());
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("russia2018pidev@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(u.getEmail()));
            message.setSubject("Promotion");
            message.setText("Bonjour, Russia2018 vous informe que le match entre "+a+" et "+ b+" qui aura lieu dans "+c+" le: "+ d +" a: "+e);
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException er) {
            throw new RuntimeException(er);
        }
    }
    }
//
} 
