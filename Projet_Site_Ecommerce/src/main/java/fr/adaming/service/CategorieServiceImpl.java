package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {
	
	// Transformation de l'association UML en Java
	@Autowired
	private ICategorieDao categorieDao;

	@Override
	public List<Categorie> getAllCategorie() {
		return categorieDao.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		return categorieDao.addCategorie(c);
	}

	@Override
	public int updateCategorie(Categorie c) {
		return categorieDao.updateCategorie(c);
	}

	@Override
	public int deleteCategorie(Categorie c) {
		return categorieDao.deleteCategorie(c);
	}

	@Override
	public Categorie getCategorieById(Categorie c) {
		return categorieDao.getCategorieById(c);
	}

}
