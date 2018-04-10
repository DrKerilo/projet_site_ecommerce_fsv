package fr.adaming.service;

import java.util.List;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

public interface ILigneCommandeService {
	public int updateLC(LigneCommande lc, Produit pr);
//	
	public int deleteLC(LigneCommande lc);
	
	public List<LigneCommande> getLigneCommande();
	
	public double getTotal();
	
	public void viderLC();

}
