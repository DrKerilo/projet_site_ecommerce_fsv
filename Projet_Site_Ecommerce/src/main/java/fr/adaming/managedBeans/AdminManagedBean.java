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
	
	
	
	// Setter pour l'injection de d�pendance
	
	// Les attribut transf�r�s � la page
	
	// constructeur vide
	
	// Getters et setters
	
	// les methodes metiers
	
	
	
}
