package fr.annuaire.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.annuaire.models.Personne;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

public interface IPersonneRepository extends JpaRepository<Personne, Long>{
	
	@Query("SELECT p FROM Personne p WHERE p.nom LIKE :nomKey")
	public Page<Personne> chercher(@Param("nomKey") String nomP, Pageable pageable);
	
	@Query("SELECT p FROM Personne p JOIN p.groupe WHERE p.groupe.id = :idKey")
	public Page<Personne> chercherParIdGroupe(@Param("idKey") Long IdGoupe, Pageable pageable);
	
	@Query("SELECT p FROM Personne p LEFT JOIN p.groupe WHERE p.groupe.id = :idKey AND p.nom LIKE :nomKey")
	public Page<Personne> chercherParIdGroupeAndNomPersonne(@Param("idKey") Long IdGoupe, @Param("nomKey") String nomPersonne, Pageable pageable);

	/*@Query("UPDATE p FROM Personne SET p.nom = :nomKey, p.prenom = :prenomKey, p.email = :emailKey,"
			+ " p.siteweb = :sitewebKey, p.dtn = :dtnKey, p.id_groupe = :idGroupeKey WHERE p.id = :idKey")
	public Page<Personne> update(@Param("nomKey") String nom, @Param("prenomKey") String prenom,
			@Param("emailKey") String email, @Param("sitewebKey") String siteweb, @Param("dtnKey") String dtn, 
			@Param("idGroupeKey") int idGroupe, @Param("idKey") int id, Pageable pageable);*/
	
	@Query(value ="UPDATE Personne SET nom = :nomKey, prenom = :prenomKey, email = :emailKey,"
			+ " siteweb = :sitewebKey, dtn = :dtnKey, id_groupe = :idGroupeKey WHERE id = :idKey", nativeQuery = true)
	public Page<Personne> update(@Param("nomKey") String nom, @Param("prenomKey") String prenom,
			@Param("emailKey") String email, @Param("sitewebKey") String siteweb, @Param("dtnKey") String dtn, 
			@Param("idGroupeKey") int idGroupe, @Param("idKey") int id, Pageable pageable);
	
	
}














