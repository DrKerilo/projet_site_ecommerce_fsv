package fr.adaming.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Service("coService") // pour pouvoir l'utiliser avec managedProperty
@Transactional // pour rendre toutes les méthodes de la classe transactionnelles
public class CommandeServiceImpl implements ICommandeService {
	@Autowired
	private ICommandeDao commandeDao;

	// le setter pour l'injection de dépendance
	public void setCommandeDao(ICommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	@Override
	public Commande finaliserCommande(List<LigneCommande> liste, Client cl) {
		return commandeDao.finaliserCommande(liste, cl);
	}

	@Override
	public void bilanPDF(Commande co, double total) {
		// TODO Auto-generated method stub

		try {
			Document document = new Document(PageSize.A4, 75, 75, 75, 75);

			StringBuilder sOut = new StringBuilder();
			String input = System.getProperty("user.home");
			for (int i = 0; i < input.length(); i++) {
				if (i > 0 && input.charAt(i) == '\\') {

					sOut.append("\\");
				}
				sOut.append(input.charAt(i));
			}

			sOut.append("\\\\Desktop\\\\PdfCommande" + co.getId() + ".pdf");

			String path = sOut.toString();
			System.out.println(path);

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
			// new
			// FileOutputStream("C:\\Users\\inti0343\\Desktop\\Formation\\PdfCommande"
			// + co.getId() + ".pdf"));
			document.open();

			Paragraph titre = new Paragraph("Toto Store\nFacture de la commande n°" + co.getId(), FontFactory
					.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE, new CMYKColor(54, 255, 201, 0)));
			titre.setSpacingAfter(20);
			document.add(titre);

			Paragraph sousTitre1 = new Paragraph(
					"Client : \n" + co.getClient().getNomClient() + " \n" + co.getClient().getAdresse() + "\n"
							+ co.getClient().getTel() + "\n" + co.getClient().getEmail(),
					FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 15));
			sousTitre1.setSpacingAfter(10);
			document.add(sousTitre1);

			Paragraph indic1 = new Paragraph("Commande effectuée le " + co.getDateCommande(),
					FontFactory.getFont(FontFactory.HELVETICA));
			indic1.setSpacingAfter(5);
			document.add(indic1);

			PdfPTable poAv = new PdfPTable(4);
			poAv.setSpacingAfter(10);
			poAv.addCell("Produit");
			poAv.addCell("Prix à l'unité");
			poAv.addCell("Quantité");
			poAv.addCell("Prix");

			for (LigneCommande ligne : co.getListeLignesCommandes()) {
				System.out.println(ligne);
				Produit produit = ligne.getProduit();

				poAv.addCell(String.valueOf(produit.getNomProduit()));
				poAv.addCell(String.valueOf(produit.getPrix()) + "€");
				poAv.addCell(String.valueOf(ligne.getQuantite()));
				poAv.addCell(String.valueOf(ligne.getPrix()) + "€");
			}

			document.add(poAv);

			// Ajouter le total

			Paragraph indic2 = new Paragraph("Total : " + total + "€", FontFactory.getFont(FontFactory.HELVETICA));
			indic2.setSpacingAfter(5);
			document.add(indic2);

			Paragraph indic3 = new Paragraph(
					"Toto Store vous remercie de votre confiance et vous souhaite une bonne journée.",
					FontFactory.getFont(FontFactory.HELVETICA));
			indic3.setSpacingAfter(5);
			document.add(indic3);

			document.close();

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void envoyerMail(Commande co) {
		String mailRecup = co.getClient().getEmail();

		// TODO Auto-generated method stub
		final String username = "clear.skies928@gmail.com";
		final String password = /* Ne soyez pas trop curieux ! */ 																																												"BubblyClouds8";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			Multipart multipart = new MimeMultipart();

			// Set From: header field of the header.
			message.setFrom(new InternetAddress("clear.skies928@gmail.com"));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailRecup));

			// Set Subject: header field
			message.setSubject("Validation de votre commande chez Toto Store - Commande n°" + co.getId());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(
					"Bonjour M. ou Mme " + co.getClient().getNomClient() + ",<br/> "
					+ "Nous vous remercions pour votre achat chez Toto Store. "
							+ "Celle-ci a bien été prise en compte par nos services et vous sera délivrée sous un délai de 5 jours ouvrés.<br/>"
							+ "Veuillez trouver ci-joint la facture de votre commande n°" + co.getId() + "<br/><br/>"
							+ "En esperant vous revoir bientôt sur notre site, <br/> " + "Toto Store - service client",
					"text/html");

			// creates body part for the attachment
			MimeBodyPart attachPart = new MimeBodyPart();
			
			StringBuilder sOut = new StringBuilder();
			String input = System.getProperty("user.home");
			for (int i = 0; i < input.length(); i++) {
				if (i > 0 && input.charAt(i) == '\\') {

					sOut.append("\\");
				}
				sOut.append(input.charAt(i));
			}

			sOut.append("\\\\Desktop\\\\PdfCommande" + co.getId() + ".pdf");
			

			String attachFile = sOut.toString();

			DataSource source = new FileDataSource(attachFile);
			attachPart.setDataHandler(new DataHandler(source));
			attachPart.setFileName(new File(attachFile).getName());

			// adds parts to the multipart
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachPart);

			// sets the multipart as message's content
			message.setContent(multipart);

			// Send message
			Transport.send(message, message.getAllRecipients());
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
