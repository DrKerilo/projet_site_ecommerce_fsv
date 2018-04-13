package fr.adaming.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

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
			
			
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(path));
//			new FileOutputStream("C:\\Users\\inti0343\\Desktop\\Formation\\PdfCommande" + co.getId() + ".pdf"));
			document.open();

			Paragraph titre = new Paragraph("Facture de la commande " + co.getId(), FontFactory
					.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE, new CMYKColor(54, 255, 201, 0)));
			titre.setSpacingAfter(20);
			document.add(titre);

			Paragraph sousTitre1 = new Paragraph(
					"Client : " + co.getClient().getNomClient() + " \n" + co.getClient().getAdresse() + "\n"
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

}
