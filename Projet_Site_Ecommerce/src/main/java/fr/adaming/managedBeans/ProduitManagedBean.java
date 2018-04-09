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

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "pMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {
	// Transformation de l'association UML en JAVA
	@ManagedProperty(value = "#{pService}")
	private IProduitService produitService;

	// Setter pour l'injection de dépendance
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	// Les attribut transférés à la page
	private Produit produit;
	HttpSession maSession;
	private List<Produit> listeProduit;
	private Long id;
	private UploadedFile uf;

	// Constructeur vide
	public ProduitManagedBean() {
		this.produit = new Produit();
	}

	@PostConstruct
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// Getters et setters
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Long getId() {
		return id;
	}

	public void setId_p(Long id) {
		this.id = id;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	// Méthodes métier
	public String ajouterProd() {
		// APPEL DE LA METHODE AJOUTER
		produit.setPhoto(this.uf.getContents());

		Produit prodOut = produitService.addProduit(produit);

		if (prodOut.getId() != 0) {
			// TODO nom de la page
			return "testFab";

		} else {
			// TODO nom de la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec ajout"));

			return "testFab";
		}
	}

	public String modifierProd() {
		int verif = produitService.updateProduit(produit);

		if (verif != 0) {
			// TODO nom de la page
			return "testFab";
		} else {
			// TODO nom de la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec modification"));

			return "testFab";
		}
	}

	public String supprimerProd() {

		int verif = produitService.deleteProduit(produit);

		if (verif != 0) {
			// TODO nom de la page
			return "testFab";
		} else {
			// TODO nom de la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("echec suppression"));

			return "testFab";
		}
	}

	public String prodByCat() {

		listeProduit = produitService.produitByCategorie(id);

		if (listeProduit != null) {
			// TODO nom de la page
			return "testFab";
		} else {
			// TODO nom de la page
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pas de produit associé à la catégorie " + id));

			return "testFab";

		}
	}

}
