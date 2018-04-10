package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao {
	@Autowired
	private SessionFactory sf;

	private void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public LigneCommande ajouterLC(LigneCommande lc) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Ajouter à la BD
		s.save(lc);

		// Récupérer le résultat
		return lc;
	}

	@Override
	public int supprimerLC(LigneCommande lc) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "DELETE FROM LigneCommande lc WHERE lc.id = :pId";

		// Recuperer le query
		Query query = s.createQuery(req);
		
		// Passage des parametres
		query.setParameter("pId", lc.getId());
		
		return query.executeUpdate();
	}

	@Override
	public int modifierLC(LigneCommande lc) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "UPDATE LigneCommande lc SET lc.quantite=:pQuantite, lc.prix=:pPrix WHERE lc.produit.id=:pId";

		// Créer la requête
		Query query = s.createQuery(req);

		// Passage des parametres
		query.setParameter("pQuantite", lc.getQuantite());
		query.setParameter("pPrix", lc.getPrix());
		query.setParameter("pId", lc.getProduit().getId());

		return query.executeUpdate();
	}

	@Override
	public List<LigneCommande> getLigneCommande() {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "FROM LigneCommande lc";

		// Recuperer le query
		Query query = s.createQuery(req);
		
		return query.list();
	}

	@Override
	public LigneCommande isExist(LigneCommande lc) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();
		
		// Requete HQL
		String req = "FROM LigneCommande lc WHERE ld.id = :pId";

		// Recuperer le query
		Query query = s.createQuery(req);

		// Passage des parametres
		query.setParameter("pId", lc.getId());

		return (LigneCommande) query.uniqueResult();
	}

	@Override
	public double getTotal() {
		// Ouvrir une session
		Session s = sf.getCurrentSession();
		
		// Requete HQL
		String req;

		// Recuperer le query
		Query query = s.createQuery(req);
		
		// Passage des parametres

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viderLC() {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req;

		// Recuperer le query
		Query query = s.createQuery(req);
		
		// Passage des parametres
		// TODO Auto-generated method stub

	}

}

