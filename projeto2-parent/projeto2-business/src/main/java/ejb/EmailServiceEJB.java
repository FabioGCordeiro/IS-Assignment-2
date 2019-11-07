package ejb;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@Stateless
public class EmailServiceEJB extends Authenticator implements EmailServiceEJBRemote {

public PasswordAuthentication getPasswordAuth(String serviceUsername, String servicePassword) {
    return new PasswordAuthentication(serviceUsername, servicePassword);
}

public void sendAccountActivationLinkToBuyer(String destinationEmail,String name) {
        // OUR EMAIL SETTINGS
        String host = "smtp.gmail.com";// Gmail
        int port = 465;
        String serviceUsername = "fabiogcordeiroo@gmail.com";
        String servicePassword = "m3m3s4r3lif3";// Our Gmail password

        Properties props = new Properties();
        props.put("mail.smtp.user", serviceUsername);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return getPasswordAuth(serviceUsername, servicePassword);
            }
        };

        Session session = Session.getInstance(props, authenticator);
        session.setDebug(true);

        // Destination of the email
        String to = destinationEmail;
        String from = serviceUsername;

        try {
            Message message = new MimeMessage(session);
            // From: is our service
            message.setFrom(new InternetAddress(from));
            // To: destination given
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Confirm your account");
            // Instead of simple text, a .html template should be added here!
            message.setText("IT'S WORKING!!!");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, port, serviceUsername, servicePassword);
            Transport.send(message, message.getAllRecipients());
            transport.close();

        }catch (AddressException e) {
            e.printStackTrace(); 
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}