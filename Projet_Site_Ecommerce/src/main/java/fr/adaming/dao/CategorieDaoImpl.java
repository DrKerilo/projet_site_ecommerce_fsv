package fr.adaming.dao;

import java.util.List;

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
		// Requ�te HQL
		String req = "FROM Categorie";
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Query
		q = session.createQuery(req);
		// Envoi de la requ�te et r�cup�ration du r�sultat
		return q.list();
	}
	
	@Override
	public Categorie addCategorie(Categorie c) {
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Ins�rer la cat�gorie c
		session.save(c);
		// R�cup�rer la cat�gorie avec son nouvel id
		return c;
	}
	
	@Override
	public int updateCategorie (Categorie c) {
		// Requ�te HQL
		String req = "UPDATE Categorie c SET c.nomCategorie=:pNom, c.photo=:pPhoto, c.description=:pDescription WHERE c.id=:pId";
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Query
		q = session.createQuery(req);
		// Passage des param�tres
		q.setParameter("pNom", c.getNomCategorie());
		q.setParameter("pPhoto", c.getPhoto());
		q.setParameter("pDescription", c.getDescription());
		q.setParameter("pId", c.getId());
		// Envoi requ�te et r�cup�ration nombre de lignes modifi�es
		return q.executeUpdate();
	}
	
	@Override
	public int  deleteCategorie (Categorie c) {
		// Requ�te HQL
		String req = "DELETE FROM Categorie c WHERE c.id=:pIDc";
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Query
		q = session.createQuery(req);
		// Passage des param�tres
		q.setParameter("pIDc", c.getId());
		// Envoi requ�te et r�cup nb lignes modifi�es
		return q.executeUpdate();
	}
	
	@Override
	public Categorie getCategorieById (Categorie c) {
		// Requ�te HQL
		String req = "FROM Categorie c WHERE c.id=:pIDc";
		// Ouvrir une session
		session = sf.getCurrentSession();
		// Query
		q = session.createQuery(req);
		// Passage du param�tre
		q.setParameter("pIDc", c.getId());
		// Envoi de la requ�te et r�cup�ration du r�sultat
		return (Categorie) q.uniqueResult();
	}
	
}
