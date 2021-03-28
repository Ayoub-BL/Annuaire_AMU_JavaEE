package fr.annuaire.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

@Entity
public class PasswordResetToken {
  
    @SuppressWarnings("unused")
	private static final int EXPIRATION = 60 * 24;
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    @SuppressWarnings("unused")
	private String token;
  
    @OneToOne(targetEntity = Personne.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "personne_id")
    private Personne personne;
  
    @SuppressWarnings("unused")
	private Date expiryDate;
}