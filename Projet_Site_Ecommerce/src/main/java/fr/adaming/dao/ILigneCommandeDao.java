package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.Query;

import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommandeDao {
	public int ajouterLC(LigneCommande lc);
	
	public int supprimerLC(LigneCommande lc);
	
	public int modifierLC(LigneCommande lc);
	
	public List<LigneCommande> getLigneCommande();
	
	public LigneCommande isExist(LigneCommande lc);
	
	public double getTotal();
	
	public void viderLC();
	
}
