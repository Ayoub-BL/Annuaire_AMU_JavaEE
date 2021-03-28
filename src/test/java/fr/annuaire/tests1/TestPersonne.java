package fr.annuaire.tests1;

import java.sql.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.annuaire.models.Groupe;
import fr.annuaire.models.Personne;

@RestController
public class TestPersonne {
	
	@RequestMapping(value = "/restpersonne", method = RequestMethod.GET)
	public Personne firstPage() {

		Groupe grp1 = new Groupe("M1 ILD 2018/2019"); // Ingénierie du logiciel et des données (ILD)
		Personne p1 = new Personne("BOULAHYA", "Ayoub", "b.a@gmail.com","www.ayoub-software.ma", Date.valueOf("2009-12-31"), grp1);
		
		return p1;
	}

}
