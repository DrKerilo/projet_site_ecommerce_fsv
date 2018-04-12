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

	// setters
	public void setLcDao(ILigneCommandeDao lcDao) {
		this.lcDao = lcDao;
	}

	public void setProdDao(IProduitDao prodDao) {
		this.prodDao = prodDao;
	}

	
	// Méthodes
	@Override
	public int updateLC(LigneCommande lc, Produit pr) {
		// TODO regarder si cohérent avec Valentin
		Produit produit = prodDao.rechercherProduit(pr);

		if (produit != null) {
			lc.setProduit(produit);

			LigneCommande lc2 = lcDao.isExist(lc);
			System.out.println(lc2);

			// Vérifier si la ligne existe pour le produit
			if (lc2 != null) {

				// Mettre à jour plutot que d'avoir un doublon
				if (lc.getQuantite() <= 0) {
					System.out.println("Je supprime");
					return lcDao.supprimerLC(lc2);
				} else {
					System.out.println("Je modifie le panier");
					// Le produit est présent dans le panier - modifier
					lc.setPrix(produit.getPrix() * lc.getQuantite());

					return lcDao.modifierLC(lc);
				}
				
			} else if (lc.getQuantite() != 0) {
				System.out.println("J'ajoute au panier");
				// Le produit n'existe pas encore dans le panier
				lc.setPrix(produit.getPrix() * lc.getQuantite());
				lc = lcDao.ajouterLC(lc);
				
				if(lc != null){
					return 1;
				}
				
				return 0;
			}
		}

		return 0;
	}

	@Override
	public List<LigneCommande> getLigneCommande() {
		return lcDao.getLigneCommande();
	}

	@Override
	public void viderLC() {
		lcDao.viderLC();
	}

	@Override
	public int deleteLC(LigneCommande lc) {
		return lcDao.supprimerLC(lc);
	}

	@Override
	public double getTotal(List<LigneCommande> list) {
		double somme = 0;
		
		for(LigneCommande lc:list){
			somme = somme + lc.getPrix();
			System.out.println(somme + " " + lc.getPrix());
		}
		
		return somme;
	}

}
