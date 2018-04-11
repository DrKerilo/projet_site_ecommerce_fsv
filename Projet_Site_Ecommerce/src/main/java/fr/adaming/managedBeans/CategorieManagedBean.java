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

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@ManagedProperty(value = "#{catService}")
	private ICategorieService categorieService;

	// Setter pour l'injection de dépendance
	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	// Attributs
	private Categorie categorie;
	private List<Categorie> listeCategories;
	private boolean indice; // pour le rendered de la vue
	private HttpSession session;
	private UploadedFile uf;

	// Constructeur vide
	public CategorieManagedBean() {
		this.categorie = new Categorie();
		this.indice = false;
	}

	@PostConstruct
	public void init() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// Getters et setters
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	// Méthodes métiers
	// Ajout catégorie
	public String ajouterCategorie() {
		// Ajouter la photo dans la catégorie
		categorie.setPhoto(uf.getContents());
		// Appel de la méthode service
		Categorie catAjout = categorieService.addCategorie(categorie);
		if (catAjout.getId() != 0) {
			// Récupérer la liste des catégories mise à jour
			listeCategories = categorieService.getAllCategorie();
			this.session.setAttribute("listeCategories", listeCategories);
			return "#";
		} else {
			// Si erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur dans l'ajout."));
			return "#";
		}
	}

	// Modifier catégorie
	public String modifierCategorie() {
		int verif = categorieService.updateCategorie(categorie);
		if (verif != 0) {
			// Récupérer la liste des catégories mises à jour
			listeCategories = categorieService.getAllCategorie();
			return "#";
		} else {
			// Si erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La catégorie indiquée n'existe pas."));
			return "#";
		}
	}

	// Supprimer catégorie
	public String supprimerCategorie() {
		int verif = categorieService.deleteCategorie(categorie);
		if (verif != 0) {
			// Récupérer la liste des catégories mises à jour
			listeCategories = categorieService.getAllCategorie();
			return "#";
		} else {
			// Si erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La catégorie indiquée n'existe pas."));
			return "#";
		}
	}

	// Rechercher catégorie
	public String consulterCategorie() {
		Categorie cOut = categorieService.getCategorieById(categorie);
		if (cOut != null) {
			categorie = cOut;
			indice = true;
			return "#";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La catégorie recherchée n'existe pas."));
			indice = false;
			return "#";
		}
	}

	// Modification de la catégorie avec rowEditor
	public void onRowEdit(RowEditEvent event) {
		categorieService.updateCategorie((Categorie) event.getObject());
		listeCategories = categorieService.getAllCategorie();
		session.setAttribute("listeCat", listeCategories);
		FacesMessage msg = new FacesMessage("Catégorie modifiée", ((Categorie) event.getObject()).getNomCategorie());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Modification annulée", ((Categorie) event.getObject()).getNomCategorie());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
