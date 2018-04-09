package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.adaming.service.IAdminService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AdminManagedBean implements Serializable {
	
	// Transformation de l'association UML en JAVA
	@ManagedProperty(value="#{adService}")
	private IAdminService adminService;
	
	
	
	// Setter pour l'injection de dépendance
	
	// Les attribut transférés à la page
	
	// constructeur vide
	
	// Getters et setters
	
	// les methodes metiers
	
	
	
}
