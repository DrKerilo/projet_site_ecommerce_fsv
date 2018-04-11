package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
	@OneToMany(mappedBy="produit", cascade=CascadeType.REMOVE)
	private List<LigneCommande> listeLignesCommandes;
	
	// Constructeurs
	public Produit() {
		super();
	}
	public Produit(String nomProduit, String description, double prix, int quantite, boolean selectionne,
			byte[] photo) {
		super();
		this.nomProduit = nomProduit;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}
	public Produit(Long id, String nomProduit, String description, double prix, int quantite, boolean selectionne,
			byte[] photo) {
		super();
		this.id = id;
		this.nomProduit = nomProduit;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}
	
	// Getters et setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public boolean isSelectionne() {
		return selectionne;
	}
	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public List<LigneCommande> getListeLignesCommandes() {
		return listeLignesCommandes;
	}
	public void setListeLignesCommandes(List<LigneCommande> listeLignesCommandes) {
		this.listeLignesCommandes = listeLignesCommandes;
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nomProduit=" + nomProduit + ", description=" + description + ", prix=" + prix
				+ ", quantite=" + quantite + ", selectionne=" + selectionne + "]";
	}
	
	
	
}
