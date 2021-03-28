package fr.annuaire.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fr.annuaire.models.Groupe;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

public interface IGroupeRepository extends JpaRepository<Groupe, Long>{
	
	@Query("SELECT g FROM Groupe g WHERE g.nom LIKE :key")
	public Page<Groupe> chercher(@Param("key") String nomG, Pageable pageable);

}
