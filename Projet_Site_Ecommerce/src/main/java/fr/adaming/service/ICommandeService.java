package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

public interface ICommandeService {
	public Commande finaliserCommande(List<LigneCommande> liste, Client cl);
	
	void bilanPDF(Commande co, double total);
}
