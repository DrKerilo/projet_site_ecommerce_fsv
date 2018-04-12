package fr.adaming.dao;

import java.util.List;

import javax.persistence.Query;

import fr.adaming.model.LigneCommande;

public interface ILigneCommandeDao {
	public LigneCommande ajouterLC(LigneCommande lc);
	
	public int supprimerLC(LigneCommande lc);
	
	public int modifierLC(LigneCommande lc);
	
	public List<LigneCommande> getLigneCommande();
	
	public LigneCommande isExist(LigneCommande lc);

	public void viderLC();
	
}
