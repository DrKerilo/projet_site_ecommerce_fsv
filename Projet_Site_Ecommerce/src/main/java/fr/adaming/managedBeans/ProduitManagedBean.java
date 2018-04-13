package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "pMB")
@SessionScoped
public class ProduitManagedBean implements Serializable {
	// Transformation de l'association UML en JAVA
	@ManagedProperty(value = "#{pService}")
	private IProduitService produitService;

	// TODO en attente de produit
	@ManagedProperty(value = "#{catService}")
	private ICategorieService categorieService;

	// Setter pour l'injection de d�pendance
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	// Les attribut transf�r�s � la page
	private Produit produit;
	HttpSession maSession;
	private List<Produit> listeProduit;
	private Categorie cat;
	private UploadedFile uf;

	// Constructeur vide
	public ProduitManagedBean() {
		this.produit = new Produit();
		this.cat = new Categorie();
		this.uf = new UploadedFileWrapper();
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

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	public Categorie getCat() {
		return cat;
	}

	public void setCat(Categorie cat) {
		this.cat = cat;
	}

	// M�thodes m�tier
	public String ajouterProd() {
		// APPEL DE LA METHODE AJOUTER
		produit.setPhoto(this.uf.getContents());
		cat = categorieService.getCategorieById(cat);
		Produit prodOut = produitService.addProduit(produit, cat);
		if (prodOut.getId() != 0) {
			return "espaceadmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("�chec de l'ajout."));
			return "ajoutadmin";
		}
	}

	public String modifierProd() {
		int verif = produitService.updateProduit(produit, cat);

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
		listeProduit = produitService.produitByCategorie(cat);

		if (listeProduit != null) {
			// TODO nom de la page
			return "success";
		} else {
			// TODO nom de la page
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Pas de produit associ� � la cat�gorie " + cat.getId()));

			return "fail";

		}
	}

	// Modification de la cat�gorie avec rowEditor
	public void onRowEdit(RowEditEvent event) {
		produitService.updateProduit((Produit) event.getObject(), this.cat);
		listeProduit = produitService.getlisteProduit();
		maSession.setAttribute("listeProduits", listeProduit);
		FacesMessage msg = new FacesMessage("Produit modifi�", ((Produit) event.getObject()).getNomProduit());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Modification annul�e", ((Produit) event.getObject()).getNomProduit());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
