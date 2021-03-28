package fr.annuaire.models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

@Entity
//Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=17028432, allocationSize=100)
@Component()
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Personne implements Serializable{
	
	// Attributes :
	private static final long serialVersionUID = 3708085921156953388L;
	@Id
    // Use the sequence that is defined above:
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private Long 	id;
    @NotNull(message = "Le nom est obligatoire!")
	private String 	nom;
    @NotNull(message = "Le prénom est obligatoire!")
	private String 	prenom;
    @NotNull(message = "L'email est obligatoire!")
	private String 	email;
    @NotNull(message = "Le siteweb est obligatoire!")
	private String 	siteweb;
    @NotNull(message = "La date de naissance est obligatoire!")
	private Date 	dtn;
	@ManyToOne
	@JoinColumn(name="id_groupe")
	private Groupe groupe;
	
	// Constructors :
	public Personne() { super(); }

	public Personne(String nom, String prenom, String email, String siteweb, Date dtn) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.siteweb = siteweb;
		this.dtn = dtn;
	}
	
	public Personne(String nom, String prenom, String email, String siteweb, Date dtn, Groupe groupe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.siteweb = siteweb;
		this.dtn = dtn;
		this.groupe = groupe;
	}
	
	// Getters & Setters :
	public static long getSerialversionuid() { return serialVersionUID; }				

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getNom() { return nom; }

	public void setNom(String nom) { this.nom = nom; }

	public String getPrenom() { return prenom; }

	public void setPrenom(String prenom) { this.prenom = prenom; }

	public String getEmail() { return email; }

	public void setEmail(String mail) { this.email = mail; }

	public String getSiteweb() { return siteweb; }

	public void setSiteweb(String siteweb) { this.siteweb = siteweb; }

	public Date getDtn() { return dtn; }

	public void setDtn(Date dtn) { this.dtn = dtn; }

	public Groupe getGroupe() { return groupe; }

	public void setGroupe(Groupe groupe) { this.groupe = groupe; }

}