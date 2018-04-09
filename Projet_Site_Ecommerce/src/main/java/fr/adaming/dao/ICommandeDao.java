package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Local
public interface ICommandeDao {
	public Commande finaliserCommande(List<LigneCommande> liste, Client cl);

}
