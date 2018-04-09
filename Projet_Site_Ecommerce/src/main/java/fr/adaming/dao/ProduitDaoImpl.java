package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {
	@Autowired
	private SessionFactory sf;

	private void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Produit addProduit(Produit prod) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Ajouter à la BD
		s.save(prod);

		// Obtenir le produit de sortie
		return prod;
	}

	@Override
	public List<Produit> getlisteProduit() {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "FROM Produit p";

		// Créer lequery
		Query query = s.createQuery(req);

		List<Produit> liste = query.list();

		return liste;
	}

	@Override
	public int updateProduit(Produit prod) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "UPDATE Produit p SET p.nomProduit = :pNomP, p.description = :pDescr, "
				+ "p.prix = :pPrix, p.quantite = :pQuant, p.selectionne = :pSel, "
				+ "p.photo = :pPhoto WHERE p.id = :pId";

		// Créer le query
		Query query = s.createQuery(req);

		// Passage des parametres
		query.setParameter("pNomP", prod.getNomProduit());
		query.setParameter("pDescr", prod.getDescription());
		query.setParameter("pPrix", prod.getPrix());
		query.setParameter("pQuant", prod.getQuantite());
		query.setParameter("pSel", prod.isSelectionne());
		query.setParameter("pPhoto", prod.getPhoto());
		query.setParameter("pId", prod.getId());

		return query.executeUpdate();
	}

	@Override
	public int deleteProduit(Produit prod) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();
		
		// Requete HQL
		String req = "DELETE FROM Produit p WHERE p.id = :pId";
		
		// Créer le query
		Query query = s.createQuery(req);

		// Passage des parametres
		query.setParameter("pId", prod.getId());
		
		return query.executeUpdate();
	}

	@Override
	public Produit rechercherProduit(Long id) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "FROM Produit p WHERE p.id = :pId";

		// Créer le query
		Query query = s.createQuery(req);

		// Passage des parametres
		query.setParameter("pId", id);

		return (Produit) query.uniqueResult();
	}

	@Override
	public List<Produit> produitByCategorie(Long id) {
		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Requete HQL
		String req = "FROM Produit p WHERE p.categorie.id = :pId";

		// Créer le query
		Query query = s.createQuery(req);

		// Passage des parametres
		query.setParameter("pId", id);

		return query.list();
	}

}
