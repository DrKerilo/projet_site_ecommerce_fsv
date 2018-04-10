package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitService {
	// AJOUTER UN PRODUIT
	public Produit addProduit(Produit prod, Categorie cat);

	// CONSULTER TOUS LES PRODUITS
	List<Produit> getlisteProduit();

	// MODIFIER UN PRODUIT
	public int updateProduit(Produit prod, Categorie cat);

	// SUPPRIMER UN PRODUIT
	public int deleteProduit(Produit prod);

	// RECHERCHER UN PRODUIT
	public Produit rechercherProduit(Produit pr);
	
	// Liste par catégorie
	public List<Produit> produitByCategorie(Categorie cat);
}
