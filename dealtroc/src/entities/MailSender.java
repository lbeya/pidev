/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

    public static void sendEmail(String toEmailAddress, String fromEmailAddress, String appPassword, String subject,
            String bodyText) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true); //If true, attempt to authenticate the user using the AUTH command. Defaults to false.
        prop.put("mail.smtp.host", "smtp.gmail.com");//The SMTP server to connect to.
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");//The SMTP server port to connect to
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");//Specifies the SSL protocols that will be enabled for SSL connections

        Session session = Session.getDefaultInstance(prop);
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(fromEmailAddress));

            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress)); // setting "TO" email address
            message.setSubject(subject); // setting subject
            message.setText(bodyText);  // setting body

            System.out.println("Sending Email...");
            Transport t = session.getTransport("smtp");

            t.connect(fromEmailAddress, appPassword);
            t.sendMessage(message, message.getAllRecipients());
            t.close();

        } catch (MessagingException e) {

            e.printStackTrace();
        }
        System.out.println("Email sent successfully...");
    }
}

//ltusslpyminftuvk

