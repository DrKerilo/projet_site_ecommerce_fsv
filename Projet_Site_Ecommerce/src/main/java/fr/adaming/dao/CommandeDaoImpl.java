package fr.adaming.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Repository
public class CommandeDaoImpl implements ICommandeDao {
	@Autowired
	private SessionFactory sf;

	private void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Commande finaliserCommande(List<LigneCommande> liste, Client cl) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Obtenir la date dans le bon format
		Date dt = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		String currentTime = sdf.format(dt);

		// TODO Auto-generated method stub
		Commande commande = new Commande(dt);
		commande.setListeLignesCommandes(liste);
		commande.setClient(cl);

		s.save(commande);

		return commande;
	}

}
