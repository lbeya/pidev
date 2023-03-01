import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

public class MailSender {

    public static void sendMail(String to, String subject, String body) throws MessagingException {

        // Définir les propriétés de l'email
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Créer une session avec l'email et le mot de passe de l'expéditeur
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lbeya99@gmail.com", "Touta710");
            }
        });

        // Créer un message email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("lbeya99@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // Envoyer le message email
        Transport.send(message);
    }
}

