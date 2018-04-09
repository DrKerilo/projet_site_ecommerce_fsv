package fr.adaming.service;

import java.util.List;

import fr.adaming.model.LigneCommande;

public interface ILigneCommandeService {
	public int updateLC(LigneCommande lc, Long id_prod);
//	
	public int deleteLC(LigneCommande lc);
	
	public List<LigneCommande> getLigneCommande();
	
	public double getTotal();
	
	public void viderLC();

}
