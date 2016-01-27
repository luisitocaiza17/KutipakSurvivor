package Utils;
import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//import org.apache.commons.net.ftp.FTPClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Utilitarios {

	// Variables envio de mail
	public static String usuarioMail    = "luis.caiza@smartwork.com.ec";
	public static String miCorreo       = "luis.caiza@smartwork.com.ec";
	public static String miContrasena   = "Luisito121";
	public static String servidorSMTP   = "smtp.office365.com";
	public static String puertoEnvio    = "587";

	public static void subirXMLFTP(String cotizacionId,String xml) {

		// Variables FTP
		//FTPClient client = new FTPClient();

		// Variables XML
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		Document doc;
		TransformerFactory tFactory;
		Transformer transformer;

		String nombreArchivo = cotizacionId+".xml";
		try {
			factory 			= DocumentBuilderFactory.newInstance();
			builder 			= factory.newDocumentBuilder();
			doc 				= builder.parse(new InputSource(new StringReader(xml)));
			// Usamos un transformador para la salida del archivo xml generado
			tFactory 			= TransformerFactory.newInstance();
			transformer 		= tFactory.newTransformer();
			DOMSource source 	= new DOMSource(doc);		    
			StreamResult result = new StreamResult(new File("/home/insis/"+nombreArchivo));
			transformer.transform(source, result);


		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {		
			e.printStackTrace();
		}

	}


	public static void envioMail(String mailReceptor, String asunto,String cuerpo) {

		Properties props = new Properties();
		props.put("mail.smtp.user", usuarioMail);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puertoEnvio);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		props.put("mail.store.protocol", "SMTP");

		javax.mail.Session session = javax.mail.Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuarioMail, miContrasena);
			}
		});

		try {	        	 
			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(miCorreo));
			message.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse(mailReceptor));
			message.setSubject(asunto);
			message.setContent(cuerpo,"text/html");				
			javax.mail.Transport.send(message);
			System.out.println("Enviado a "+mailReceptor);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}		
	}

	public static void envioMail(String mailReceptor, String mailCopyTO , String asunto,String cuerpo) {

		Properties props = new Properties();
		props.put("mail.smtp.user", usuarioMail);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puertoEnvio);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		props.put("mail.store.protocol", "SMTP");

		javax.mail.Session session = javax.mail.Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuarioMail, miContrasena);
			}
		});

		try {	        	 
			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(miCorreo));
			message.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse(mailReceptor));
			message.setRecipients(javax.mail.Message.RecipientType.CC,InternetAddress.parse(mailCopyTO));
			message.setSubject(asunto);
			message.setContent(cuerpo,"text/html");				
			javax.mail.Transport.send(message);
			System.out.println("Enviado a "+mailReceptor);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}		
	}


	public static String generarCodigoAleatorioPorLongitud(int longitud){

		String NUMEROS = "0123456789";
		String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
		String ESPECIALES = "!#$%^&*_=`~|/;:,.?�-�+@"; 
		String clave = "";
		String key = NUMEROS + MAYUSCULAS + MINUSCULAS; // si se desea agregar caracteres especiales poner en key ESPECIALES		 
		for (int i = 0; i < longitud; i++) {
			clave+=(key.charAt((int)(Math.random() * key.length())));
		}
		return clave; 

	}	 

	public static String encriptacionClave(String clave){		          
		try {

			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(clave.getBytes());
			byte messageDigest[] = md.digest();
			StringBuffer cadenaHexadecimal = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				cadenaHexadecimal.append(Integer.toHexString(0xFF & messageDigest[i]));
			}              
			clave = cadenaHexadecimal.toString();

		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}                             
		return clave;
	}

	public static String numeroRandomico(int length){		          
		String retorno="";

		while(retorno.length()<length){

			Random rand = new Random();
			int randomNum = rand.nextInt(10);
			retorno+=randomNum;
		}
		return retorno;
	}


	// Metodo para agregar dias a una fecha timestamp
	public static long agregarDiaFechaTimestamp(Timestamp date,int dias){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_WEEK, dias); // agregamos los dias a la fecha timestamp ingresada		 
		Timestamp ts = new Timestamp(cal.getTime().getTime());
		return ts.getTime();
	}

	// Metodo para verificar si es ano bisiesto
	public static boolean esAnoBisiesto(int year) {
		assert year >= 1583; // not valid before this date.
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}

	public static void envioMailPDFAdjunto(String mailReceptor, String asunto,String cuerpo,byte [] adjunto) {

		Properties props = new Properties();
		props.put("mail.smtp.user", usuarioMail);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puertoEnvio);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		props.put("mail.store.protocol", "SMTP");

		javax.mail.Session session = javax.mail.Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuarioMail, miContrasena);
			}
		});

		try {	        	 
			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(miCorreo));
			message.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse(mailReceptor));
			message.setSubject(asunto);
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart mp = new MimeMultipart();

			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(cuerpo, "text/html");
			mp.addBodyPart(htmlPart);

			MimeBodyPart attachment = new MimeBodyPart();
			attachment.setFileName("CertificadoCotizacion.pdf");
			attachment.setContent(adjunto, "application/pdf");
			mp.addBodyPart(attachment);

			message.setContent(mp);

			Transport.send(message);
			System.out.println("enviado a "+mailReceptor);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}		
	}
	
	
	
	public static boolean validarCedula(String cedula) {
		int[] modulo9 = {2, 1, 2, 1, 2, 1, 2, 1, 2};
		boolean valorRetorno = true;
		BigDecimal verif = new BigDecimal(0);
		if (cedula.length() != 10)
			valorRetorno = false;
		else {
			for (int i = 0; i < 9; i++) {
				BigDecimal temp = new BigDecimal(new BigDecimal(cedula.substring(i, (i + 1))).multiply(new BigDecimal(modulo9[i])).toString());
				if (temp.doubleValue() > 9)
					temp = temp.subtract(new BigDecimal("9"));
				verif = verif.add(temp);
			}
			if (verif.doubleValue() % 10 == 0)
				if (Integer.parseInt(cedula.substring(9, 10)) == 0)
					valorRetorno = true;
				else
					valorRetorno = false;
			else if ((10 - (verif.doubleValue() % 10)) == Integer.parseInt(cedula.substring(9, 10)))
				valorRetorno = true;
			else
				valorRetorno = false;
		}
		return valorRetorno;
	}
	public static boolean validaRUC(String RUC) {
		int[] modulo11 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		boolean valorRetorno = true;
		BigDecimal verif = new BigDecimal(0);
		if (RUC.length() < 13)
			valorRetorno = false;
		else if (Integer.parseInt(RUC.substring(0, 2)) < 1 || Integer.parseInt(RUC.substring(0, 2)) > 22) {
			valorRetorno = false;
		} else {
			if (Integer.parseInt(RUC.substring(2, 3)) < 0 ||
					(Integer.parseInt(RUC.substring(2, 3)) > 6 &&
							Integer.parseInt(RUC.substring(2, 3)) < 9)) {
				valorRetorno = false;
			} else {
				if (Integer.parseInt(RUC.substring(2, 3)) == 9) { //sociedad privada o extranjeros
					if (!RUC.substring(10, 13).equals("001"))
						valorRetorno = false;
					else {
						modulo11[0] = 4;
						modulo11[1] = 3;
						modulo11[2] = 2;
						modulo11[3] = 7;
						modulo11[4] = 6;
						modulo11[5] = 5;
						modulo11[6] = 4;
						modulo11[7] = 3;
						modulo11[8] = 2;
						for (int i = 0; i < 9; i++) {
							verif = verif.add(new BigDecimal(RUC.substring(i, (i + 1))).multiply(new BigDecimal(modulo11[i])));
						}
						if (verif.doubleValue() % 11 == 0)
							if (Integer.parseInt(RUC.substring(9, 10)) == 0)
								valorRetorno = true;
							else
								valorRetorno = false;
						else if ((11 - (verif.doubleValue() % 11)) == Integer.parseInt(RUC.substring(9, 10)))
							valorRetorno = true;
						else
							valorRetorno = false;
					}
				} else if (Integer.parseInt(RUC.substring(2, 3)) == 6) { //sociedad p�blicas
					if (!RUC.substring(10, 13).equals("001"))
						valorRetorno = false;
					else {
						modulo11[0] = 3;
						modulo11[1] = 2;
						modulo11[2] = 7;
						modulo11[3] = 6;
						modulo11[4] = 5;
						modulo11[5] = 4;
						modulo11[6] = 3;
						modulo11[7] = 2;
						for (int i = 0; i < 8; i++) {
							verif = verif.add(new BigDecimal(RUC.substring(i, (i + 1))).multiply(new BigDecimal(modulo11[i])));
						}
						if (verif.doubleValue() % 11 == 0)
							if (Integer.parseInt(RUC.substring(8, 9)) == 0)
								valorRetorno = true;
							else
								valorRetorno = false;
						else if ((11 - (verif.doubleValue() % 11)) == Integer.parseInt(RUC.substring(8, 9)))
							valorRetorno = true;
						else
							valorRetorno = false;
					}
				} else if (Integer.parseInt(RUC.substring(2, 3)) < 6 && Integer.parseInt(RUC.substring(2, 3)) >= 0) { //personas naturales
					if (!RUC.substring(10, 13).equals("001"))
						valorRetorno = false;
					else {
						valorRetorno = validarCedula(RUC.substring(0, 10));
					}
				}
			}
		}
		return valorRetorno;
	}
}