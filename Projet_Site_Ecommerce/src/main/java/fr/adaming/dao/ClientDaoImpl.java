package fr.adaming.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository
public class ClientDaoImpl implements IClientDao {
	@Autowired
	private SessionFactory sf;

	private void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Client addClient(Client cl) {
		Session s = sf.getCurrentSession();

		s.save(cl);

		return cl;
	}

}
