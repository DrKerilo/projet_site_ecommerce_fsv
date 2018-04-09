package fr.adaming.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.LigneCommande;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao{
	@Autowired
	private SessionFactory sf;
	
	private void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public int ajouterLC(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int supprimerLC(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifierLC(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LigneCommande> getLigneCommande() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LigneCommande isExist(LigneCommande lc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viderLC() {
		// TODO Auto-generated method stub
		
	}
	
	

}
