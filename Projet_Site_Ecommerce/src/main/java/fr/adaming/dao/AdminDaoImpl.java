package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {
	@Autowired
	private SessionFactory sf;
	
	private void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Admin isExist(Admin a) {
		// Requete HQL
		String req = "FROM Admin a WHERE a.mail = :pMail AND a.mdp = :pMdp";

		// Ouvrir une session
		Session s = sf.getCurrentSession();

		// Recuperer le query
		Query query = s.createQuery(req);

		// Passage des parametres
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());
		
		return (Admin) query.uniqueResult();
	}

}
