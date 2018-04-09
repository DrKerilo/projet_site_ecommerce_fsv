package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.dao.IProduitDao;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Service("lcService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService {
	@Autowired
	private ILigneCommandeDao lcDao;

	@Autowired
	private IProduitDao prodDao;

	public void setLcDao(ILigneCommandeDao lcDao) {
		this.lcDao = lcDao;
	}

	public void setProdDao(IProduitDao prodDao) {
		this.prodDao = prodDao;
	}

	@Override
	public int updateLC(LigneCommande lc, Long id_prod) {
		// TODO regarder si cohérent avec Valentin
		Produit produit = prodDao.rechercherProduit(id_prod);

		if (produit != null) {
			lc.setProduit(produit);

			LigneCommande lc2 = lcDao.isExist(lc);

			// Vérifier si la ligne existe pour le produit 
			if (lc2 != null) {
				
				// Mettre à jour plutot que d'avoir un doublon
				if (lc.getQuantite() <= 0) {
					return lcDao.supprimerLC(lc2);
				} else {
					// Le produit est présent dans le panier - modifier
					lc.setPrix(produit.getPrix() * lc.getQuantite());

					return lcDao.modifierLC(lc);
				}
			} else if (lc.getQuantite() != 0) {
				// Le produit n'existe pas encore dans le panier 
				lc.setPrix(produit.getPrix() * lc.getQuantite());
				return lcDao.ajouterLC(lc);
			}

		}

		return 0;
	}

	@Override
	public int deleteLC(LigneCommande lc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LigneCommande> getLigneCommande() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viderLC() {
		// TODO Auto-generated method stub

	}

}
