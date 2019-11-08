package ejb;

import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import data.Item;
import ejb.ItemsEJBRemote;

@Startup
@Singleton
public class EmailServiceEJB extends Authenticator implements EmailServiceEJBRemote {

@EJB
ItemsEJBRemote ejbremote;

public PasswordAuthentication getPasswordAuth(String serviceUsername, String servicePassword) {
    return new PasswordAuthentication(serviceUsername, servicePassword);
}

@Schedule(second = "30", minute="*", hour="*", info="Envia Email",persistent = false)
public void sendAccountActivationLinkToBuyer() {
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
        String to = "fabiocordeiro1998@gmail.com";
        String from = serviceUsername;

        List<Item> newestItems = ejbremote.getNewestItems();


        String finalMessage = "<h2>Are you interested in buying anything? Here are our newest items!</h2><br>";

        for (Item item : newestItems) {
            finalMessage += item.toString() + "<br><br><br>";
        }

        try {
            Message message = new MimeMessage(session);
            // From: is our service
            message.setFrom(new InternetAddress(from));
            // To: destination given
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("MyBay Catalog - Our 3 newest items");
            // Instead of simple text, a .html template should be added here!
            message.setContent(finalMessage, "text/html; charset=UTF-8");

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