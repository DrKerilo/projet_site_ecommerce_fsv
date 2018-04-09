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
		
		String req = "FROM Categorie";
		session = sf.getCurrentSession();
		q = session.createQuery(req);
		
		return q.list();
	}
	
	@Override
	public Categorie addCategorie(Categorie c) {

		session = sf.getCurrentSession();
		session.save(c);
		return c;
	}
	
	@Override
	public int updateCategorie (Categorie c) {
		
		String req = "UPDATE Categorie c SET c.nomCategorie=:pNom, c.photo=:pPhoto, c.description=:pDescription WHERE c.idCategorie=:pId";
		session = sf.getCurrentSession();
		q = session.createQuery(req);
		q.setParameter("pNom", c.getNomCategorie());
		q.setParameter("pPhoto", c.getPhoto());
		q.setParameter("pDescription", c.getDescription());
		
		return q.executeUpdate();
	}
	
	@Override
	public int  deleteCategorie (Categorie c) {
		
		String req = "DELETE FROM Categorie c WHERE c.id=:pIDc";
		session = sf.getCurrentSession();
		q = session.createQuery(req);
		q.setParameter("pIDc", c.getId());
		
		return q.executeUpdate();
	}
	
	@Override
	public Categorie getCategorieById (Categorie c) {
		
		String req = "FROM Categorie c WHERE c.id=:pIDc";
		session = sf.getCurrentSession();
		q = session.createQuery(req);
		q.setParameter("pIDc", c.getId());
		
		return (Categorie) q.uniqueResult();
	}
	
}
