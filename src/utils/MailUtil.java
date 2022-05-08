package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import model.Prilog;

public class MailUtil {

	private static final String authServer = ""; //set mail server
	private static final String user = ""; //set username
	private static final String password = ""; //set password

	public static List<Prilog> prilozi = new ArrayList<Prilog>();
	
	public static void send() {
		
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", authServer);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.trust", authServer);
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}	
		});
		
		Message message = prepareMessage(session);
		
		try {
			addAttachments(message,prilozi);
			Transport.send(message);
			System.out.println("Sent");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Message prepareMessage(Session session) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress("3riny3s@protonmail.com"));
			message.setSubject("[BMS] Racuni neplaceni");
			return message;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void addAttachments(Message message, List<Prilog> prilozi) throws MessagingException{
		MimeMultipart mp = new MimeMultipart();
		MimeBodyPart text = new MimeBodyPart();
		text.setText("U prilogu se nalaze racuni za placanje");
		mp.addBodyPart(text);

		for(Prilog prilog : prilozi) {
			MimeBodyPart bodyPart = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(prilog.getPath());
			bodyPart.setDataHandler(new DataHandler(fds));
			bodyPart.setFileName(prilog.getNaziv());
			mp.addBodyPart(bodyPart);
		}
		
		message.setContent(mp);
	}
}
