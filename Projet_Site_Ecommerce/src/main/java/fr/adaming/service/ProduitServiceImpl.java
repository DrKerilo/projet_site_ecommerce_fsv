package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Produit;

@Service("pService")
@Transactional
public class ProduitServiceImpl implements IProduitService{
	@Autowired
	private IProduitDao produitDao;
	
	// Setter pour l'injection de dépendance
	public void setProduitDao(IProduitDao produitDao) {
		this.produitDao = produitDao;
	}

	// Méthodes
	@Override
	public Produit addProduit(Produit prod) {
		return produitDao.addProduit(prod);
	}

	@Override
	public List<Produit> getlisteProduit() {
		return produitDao.getlisteProduit();
	}

	@Override
	public int updateProduit(Produit prod) {
		return produitDao.updateProduit(prod);
	}

	@Override
	public int deleteProduit(Produit prod) {
		return produitDao.deleteProduit(prod);
	}

	@Override
	public Produit rechercherProduit(Long id) {
		return produitDao.rechercherProduit(id);
	}

	@Override
	public List<Produit> produitByCategorie(Long id) {
		return produitDao.produitByCategorie(id);
	}

}
