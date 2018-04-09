package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieDao {
	
	public List<Categorie> getAllCategorie();
	
	public Categorie addCategorie(Categorie c);

	public int updateCategorie(Categorie c);

	public int deleteCategorie(Categorie c);

	public Categorie getCategorieById(Categorie c);



}
