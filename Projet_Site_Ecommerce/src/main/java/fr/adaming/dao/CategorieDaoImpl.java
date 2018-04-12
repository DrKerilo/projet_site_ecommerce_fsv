package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired
	private SessionFactory sf;

	private Session session;
	private Query q;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// Requête HQL
		String req = "FROM Categorie";
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Query
		q = session.createQuery(req);
		// Récupération du résultat
		List<Categorie> listeOut = q.list();
		// Chargement des images
		for (Categorie cat : listeOut) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return listeOut;
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Insérer la catégorie c
		session.save(c);
		// Récupérer la catégorie avec son nouvel id
		return c;
	}

	@Override
	public int updateCategorie(Categorie c) {
		// Requête HQL
		String req = "UPDATE Categorie c SET c.nomCategorie=:pNom, c.photo=:pPhoto, c.description=:pDescription WHERE c.id=:pId";
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Query
		q = session.createQuery(req);
		// Passage des paramètres
		q.setParameter("pNom", c.getNomCategorie());
		q.setParameter("pPhoto", c.getPhoto());
		q.setParameter("pDescription", c.getDescription());
		q.setParameter("pId", c.getId());
		// Envoi requête et récupération nombre de lignes modifiées
		return q.executeUpdate();
	}

	@Override
	public int deleteCategorie(Categorie c) {
		// Requête HQL
		String req = "DELETE FROM Categorie c WHERE c.id=:pIDc";
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Query
		q = session.createQuery(req);
		// Passage des paramètres
		q.setParameter("pIDc", c.getId());
		// Envoi requête et récup nb lignes modifiées
		return q.executeUpdate();
	}

	@Override
	public Categorie getCategorieById(Categorie c) {
		// Requête HQL
		String req = "FROM Categorie c WHERE c.id=:pIDc";
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Query
		q = session.createQuery(req);
		// Passage du paramètre
		q.setParameter("pIDc", c.getId());
		// Récupération du résultat
		Categorie catOut = (Categorie) q.uniqueResult();
		// Chargement de l'image
		catOut.setImage("data:image/png;base64," + Base64.encodeBase64String(catOut.getPhoto()));
		// Envoi de la requête et récupération du résultat
		return catOut;
	}

}
