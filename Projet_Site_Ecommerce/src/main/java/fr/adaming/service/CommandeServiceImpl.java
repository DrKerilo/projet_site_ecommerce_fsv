package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

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
		
//		
//		Document document = new Document(PageSize.A4, 75, 75,75, 75);
//		
//		PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\int0348\\Desktop\\Formation\\PdfCommande" + commande.getIdCommande() + ".pdf"));
//		document.open();
//		
//		Paragraph titre = new Paragraph("Facure de la commande " + commande.getIdCommande(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE, new CMYKColor(54,255,201,0)));
//		titre.setSpacingAfter(20);
//		document.add(titre);
//		
//		Paragraph sousTitre1=new Paragraph("Client : "+ commande.getClient().getNomClient() + 
//				" \n" + commande.getClient().getAdresse() + "\n" + commande.getClient().getTel()  "\n" + commande.getClient().getEmail(), 
//				FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE,15));
//		sousTitre1.setSpacingAfter(10);
//		document.add(sousTitre1);
//
//		Paragraph indic1=new Paragraph("Commande effectuée le " + commande.getDateCommande(), FontFactory.getFont(FontFactory.HELVETICA));
//		indic1.setSpacingAfter(5);
//		document.add(indic1);
//		
//		PdfPTable poAv= new PdfPTable(5);
//		poAv.setSpacingAfter(10);
//		poAv.addCell("Produit");
//		poAv.addCell("Prix à l'unité");
//		poAv.addCell("Quantité");
//		poAv.addCell("Prix");
	
//		for(LigneCommande ligne : co.getLigneCommandeListe()){
//			Produit produit = ligne.getProduit();
//
//			poAv.addCell(String.valueOf(produit.getDesignation()));
//			poAv.addCell(String.valueOf(produit.getPrix()));
//			poAv.addCell(String.valueOf(ligne.getQuantite()));
//			poAv.addCell(String.valueOf(ligne.getPrix()));
//		}
//		document.add(poAv);
//	
//		// Ajouter le total
//		
//		// Paragraph indic2=new Paragraph("Total : " + total + "€",FontFactory.getFont(FontFactory.HELVETICA));
//		// indic2.setSpacingAfter(5);
//		// document.add(indic2);
//		
//		Paragraph indic2=new Paragraph("Nous vous remercions de votre confiance et vous souhaitons une bonne journée.",FontFactory.getFont(FontFactory.HELVETICA));
//		indic2.setSpacingAfter(5);
//		
//		
//		document.close();
//				
	}

}
