package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

public interface ICommandeDao {
	public Commande finaliserCommande(List<LigneCommande> liste, Client cl);

}
