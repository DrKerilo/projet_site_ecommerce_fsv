package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="produits")
public class Produit implements Serializable {
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prod")
	private Long id;
	private String nomProduit;
	private String description;
	private double prix;
	private int quantite;
	@Column(columnDefinition="TINYINT(1)")
	private boolean selectionne;
	@Lob
	private byte[] photo;
	@Transient
	private String image;
	
	// Transformation association UML en Java
	// ManyToOne avec Categorie
	@ManyToOne
	@JoinColumn(name="cat_id", referencedColumnName="id_cat")
	private Categorie categorie;
	// OneToMany avec LigneCommande (+ si on supprimer le produit, on supprime les LigneCommande
	@OneToMany(mappedBy="produit")
	private List<LigneCommande> listeLignesCommandes;
	

}
