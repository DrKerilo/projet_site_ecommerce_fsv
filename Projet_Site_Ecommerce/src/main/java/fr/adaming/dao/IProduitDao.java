package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitDao {
	// AJOUTER UN PRODUIT
	public Produit addProduit(Produit prod);

	// CONSULTER TOUS LES PRODUITS
	List<Produit> getlisteProduit();

	// MODIFIER UN PRODUIT
	public int updateProduit(Produit prod);

	// SUPPRIMER UN PRODUIT
	public int deleteProduit(Produit prod);

	// RECHERCHER UN PRODUIT
	public Produit rechercherProduit(Produit p);
	
	// LISTE PAR CATEGORIE
	public List<Produit> produitByCategorie(Categorie Cat);

}
