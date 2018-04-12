package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "lcMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable {
	// Transformation de l'association UML en JAVA
	@ManagedProperty(value = "#{lcService}")
	ILigneCommandeService lCommandeService;
	@ManagedProperty(value = "#{pService}")
	IProduitService prService;

	// Setter pour l'injection de dépendance
	public void setlCommandeService(ILigneCommandeService lCommandeService) {
		this.lCommandeService = lCommandeService;
	}

	public void setPrService(IProduitService prService) {
		this.prService = prService;
	}

	// Les attribut transférés à la page
	private LigneCommande lc;
	private Produit pr;
	HttpSession maSession;
	private Long idTest;
	private int quantite;

	// Constructeur vide
	public LigneCommandeManagedBean() {
		this.lc = new LigneCommande();
		this.pr = new Produit();
	}

	@PostConstruct
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// Getters et setters
	public LigneCommande getLc() {
		return lc;
	}

	public void setLc(LigneCommande lc) {
		this.lc = lc;
	}

	public Produit getPr() {
		return pr;
	}

	public void setPr(Produit pr) {
		this.pr = pr;
	}
	
	

	public Long getIdTest() {
		return idTest;
	}

	public void setIdTest(Long idTest) {
		this.idTest = idTest;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	// Méthodes metiers
	public String updateLC(){
		System.out.println(lc);
		System.out.println(pr);
		System.out.println(idTest);
		System.out.println(quantite);
		
		int verif = lCommandeService.updateLC(lc, pr);
		
		if(verif != 0){
			List<LigneCommande> liste = lCommandeService.getLigneCommande();
			this.maSession.setAttribute("panier", liste);
			
			// TODO modifier la page de sortie
			return "panier";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur au cours de la modification du panier"));
			// TODO modifier la page de sortie
			return "clientProduitsParCat";
		}
	}
	
	public String supprimerLC(){
		lc.setQuantite(0);
		
		System.out.println(pr);
		System.out.println(lc);
		
		int verif = lCommandeService.updateLC(lc, pr);
		
		if(verif == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur au cours de la modification du panier"));
		} else {
			List<LigneCommande> liste = lCommandeService.getLigneCommande();
			this.maSession.setAttribute("panier", liste);
		}
		// TODO modifier la page de sortie
		return "testFab";

	}

}
