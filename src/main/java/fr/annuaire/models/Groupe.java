package fr.annuaire.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

@Entity
public class Groupe implements Serializable{

	private static final long serialVersionUID = 3708085921156953388L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long 	id;
	@NotNull(message = "Le nom est obligatoire!")
	private String 	nom;
	@OneToMany(mappedBy="groupe", fetch=FetchType.LAZY)
	private Collection<Personne> personnes;
	
	public Groupe() {
		super();
	}
	
	public Groupe(String nom) {
		super();
		this.nom = nom;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Collection<Personne> personnes) {
		this.personnes = personnes;
	}
	
}
