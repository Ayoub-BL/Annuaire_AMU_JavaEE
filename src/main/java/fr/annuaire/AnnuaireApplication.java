package fr.annuaire;

import java.sql.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.annuaire.dao.IGroupeRepository;
import fr.annuaire.dao.IPersonneRepository;
import fr.annuaire.models.Groupe;
import fr.annuaire.models.Personne;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

@SpringBootApplication
public class AnnuaireApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(AnnuaireApplication.class, args);
		
		System.out.println("\t******   Welcome to AMU-Anuaire_v.0.0.1 © 2019   ******\n");
		
		Groupe grp1 = new Groupe("M1 ILD 2018/2019");	// Ingénierie du logiciel et des données (ILD)
		Groupe grp2 = new Groupe("M1 FSI 2018/2019");	// Fiabilité et sécurité informatique (FSI)
		Groupe grp3 = new Groupe("M1 GIG 2018/2019");	// Géométrie et informatique graphique (GIG)
		Groupe grp4 = new Groupe("M1 IAAA 2018/2019");	// Intelligence artificielle et apprentissage automatique (IAAA)
		Groupe grp5 = new Groupe("M1 IMD 2018/2019");	// Informatique et mathématiques discrètes (IMD)
		Groupe grp6 = new Groupe("M1 CMB 2018/2019");	// Computational and mathematical biology (CMB)
		
		IGroupeRepository iGroupeRepository = ctx.getBean(IGroupeRepository.class);
		iGroupeRepository.save(grp1);
		iGroupeRepository.save(grp2);
		iGroupeRepository.save(grp3);
		iGroupeRepository.save(grp4);
		iGroupeRepository.save(grp5);
		iGroupeRepository.save(grp6);
		
		IPersonneRepository iPersonneRepository = ctx.getBean(IPersonneRepository.class);
		iPersonneRepository.save(new Personne("BOULAHYA", "Ayoub", "b.a@gmail.com","www.ayoub-software.ma", Date.valueOf("1999-12-31"), grp1));
		iPersonneRepository.save(new Personne("EL-ASKARI", "Karima", "e.k@gmail.com","www.ayoub-software.ma", Date.valueOf("1999-12-31"), grp1));
		iPersonneRepository.save(new Personne("SHELBY", "Thomas", "s.t@gmail.com","www.ayoub-software.ma", Date.valueOf("1999-12-31"), grp1));
		iPersonneRepository.save(new Personne("HANDERSON", "David", "h.d@gmail.com","www.ayoub-software.ma", Date.valueOf("1999-08-31"), grp2));
		iPersonneRepository.save(new Personne("IDRISSI", "Rabia", "i.r@gmail.com","www.ayoub-software.ma", Date.valueOf("1999-11-31"), grp3));
		iPersonneRepository.save(new Personne("Macaron", "Emmanuel", "m.e@gmail.com","www.ayoub-software.ma", Date.valueOf("1999-09-12"), grp4));
		iPersonneRepository.save(new Personne("BENNANI", "Karim", "b.k@gmail.com","www.ayoub-software.ma", Date.valueOf("1999-10-13"), grp5));
		iPersonneRepository.save(new Personne("QAMAR", "Ali", "q.a@gmail.com","www.ayoub-software.ma", Date.valueOf("1999-02-18"), grp6));
		
		System.out.println("Creatd person list :");
		iPersonneRepository.findAll().forEach(p->System.out.println(p.getPrenom()));
		
	}

}
