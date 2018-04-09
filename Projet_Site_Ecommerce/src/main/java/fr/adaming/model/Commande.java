package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="commandes")
public class Commande implements Serializable {
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_com")
	private Long id;
	private Date dateCommande;
	
	// Transformation association UML en Java
	@ManyToOne
	@JoinColumn(name="cl_id", referencedColumnName="id_cl")
	private Client client;
	@OneToMany(mappedBy="commande", cascade=CascadeType.REMOVE)
	private List<LigneCommande> listeLignesCommandes;
	
	// Constructeurs
	public Commande() {
		super();
	}
	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}
	public Commande(Long id, Date dateCommande) {
		super();
		this.id = id;
		this.dateCommande = dateCommande;
	}
	
	// Getters et setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<LigneCommande> getListeLignesCommandes() {
		return listeLignesCommandes;
	}
	public void setListeLignesCommandes(List<LigneCommande> listeLignesCommandes) {
		this.listeLignesCommandes = listeLignesCommandes;
	}
	

}
