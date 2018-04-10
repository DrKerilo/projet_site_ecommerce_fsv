package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieService {

	public List<Categorie> getAllCategorie();

	public Categorie addCategorie(Categorie c);

	public int updateCategorie(Categorie c);

	public int deleteCategorie(Categorie c);

	public Categorie getCategorieById(Categorie c);

}
