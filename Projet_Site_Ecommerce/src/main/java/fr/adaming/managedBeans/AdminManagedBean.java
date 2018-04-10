package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AdminManagedBean implements Serializable {

	// Transformation de l'association UML en JAVA
	@ManagedProperty(value = "#{adService}")
	private IAdminService adminService;

	@ManagedProperty(value = "#{catService}")
	private ICategorieService categorieService;

	// Setters pour l'injection de dépendance
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	// Les attribut transférés à la page
	private Admin admin;
	private List<Categorie> listeCategories;

	// constructeur vide
	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// Getters et setters
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	// les methodes metiers
	public String seConnecter(){
		Admin aOut=adminService.isExist(admin);
		if(aOut!=null){
			// Récupérer la liste des catégories
			listeCategories=categorieService.getAllCategorie();
			// Ajouter les données dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", aOut);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCat", listeCategories);
			// TODO ajouter navigation
			return "#";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admin introuvable"));
			return "accueil";
		}
	}
	
	public String seDeconnecter(){
		// Fermer la session HTTP courante
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "accueil";
	}
}
