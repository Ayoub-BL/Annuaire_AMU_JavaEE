package fr.annuaire.conrollers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fr.annuaire.dao.IGroupeRepository;
import fr.annuaire.dao.IPersonneRepository;
import fr.annuaire.models.Groupe;
import fr.annuaire.models.Personne;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

@Controller
public class PersonneController {
	
	@Autowired
	private IPersonneRepository iPersonneRepository;
	
	@Autowired
	private IGroupeRepository iGroupeRepository;
	
	@RequestMapping( value="/personnes", method=RequestMethod.GET )
	public String personnes(
			Model model,
			@RequestParam(name="idGroupe") Long idG,
			@RequestParam(name="nomPersonne", defaultValue="") String np,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="taille", defaultValue="5") int t) {
		
		p = 0;
		
		Groupe groupe = iGroupeRepository.findById(idG).get();
		try {
			@SuppressWarnings("deprecation")
			Page<Personne> pagePersonnes = iPersonneRepository.chercherParIdGroupe(idG,new PageRequest(p, t));
			model.addAttribute("att_nomPersonne", np);
			if(pagePersonnes.isEmpty()) {
				model.addAttribute("att_emptyList", "Le nom de la personne que vous avez entrer n'existe pas dans le groupe <b>" + groupe.getNom() + "</b> !");
			} else {
				model.addAttribute("att_personneList", pagePersonnes);
			}
			
			model.addAttribute("att_nomGroupe",groupe.getNom());
			int[] pages = new int[pagePersonnes.getTotalPages()];
			model.addAttribute("att_pages", pages);
			model.addAttribute("att_taille", t);
			model.addAttribute("att_page", p);
			model.addAttribute("att_idGroupe", idG);
		} catch (Exception e) {
			model.addAttribute("att_exception", e.getMessage());
		}
		
		return "personnes"; // ==> personnes.html
	}
	
	@RequestMapping( value="/chercherPersonne", method=RequestMethod.GET )
	public String chercherPersonne(
			Model model,
			@RequestParam(name="idGroupe") Long idG,
			@RequestParam(name="nomPersonne", defaultValue="") String np,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="taille", defaultValue="5") int t) {
		
		Groupe groupe = iGroupeRepository.findById(idG).get();
		try {
			@SuppressWarnings("deprecation")
			Page<Personne> pagePersonnes = iPersonneRepository.chercherParIdGroupeAndNomPersonne(idG,"%"+np+"%",new PageRequest(p, t));
			model.addAttribute("att_nomPersonne", np);
			if(pagePersonnes.isEmpty()) {
				model.addAttribute("att_emptyList", "Le nom de la personne que vous avez entrer n'existe pas dans le groupe \"" + groupe.getNom() + "\" !");
			} else {
				model.addAttribute("att_personneList", pagePersonnes);
			}
			model.addAttribute("att_nomGroupe",groupe.getNom());
			int[] pages = new int[pagePersonnes.getTotalPages()];
			model.addAttribute("att_pages", pages);
			model.addAttribute("att_taille", t);
			model.addAttribute("att_page", p);
			model.addAttribute("att_idGroupe", idG);
		} catch (Exception e) {
			model.addAttribute("att_exception", e.getMessage());
		}
		
		return "personnes"; // ==> personnes.html
	}
	
	@RequestMapping( value="/affichePersonne", method=RequestMethod.GET )
	public String affichePersonne(
			Model model,
			@RequestParam(name="idPersonne") Long idP,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="taille", defaultValue="5") int t,
			HttpSession session) {
		
		Personne sessonPersonne = (Personne) session.getAttribute("MY_SESSION_PERSONNE");
		String sessionIdPersonne;
		if (sessonPersonne == null) { sessionIdPersonne = ""; }
		else { sessionIdPersonne = sessonPersonne.getId().toString(); }
		model.addAttribute("att_sessionIdPersonne", sessionIdPersonne);
		
		Personne personne = iPersonneRepository.findById(idP).get();
		
		if(personne == null) { return "error"; }
		
		model.addAttribute("att_personne", personne);
		model.addAttribute("att_taille", t);
		model.addAttribute("att_page", p);
		
		return "personne"; // ==> personne.html
	}
	
	@RequestMapping( value="/modifierPersonne", method=RequestMethod.GET )
	public String modifierPersonne(
			Model model,
			@ModelAttribute Personne personneParam,
			@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="taille", defaultValue="5") int taille,
			HttpSession session) {
		
		page--;
		
		model.addAttribute("att_sessionIdPersonne", personneParam.getId());
		
		Personne personne = iPersonneRepository.getOne(personneParam.getId());
		
		if( personne == null ) {
			return "error"; // ==> error.html
		}
		
		personne.setNom(personneParam.getNom());
		personne.setPrenom(personneParam.getPrenom());
		personne.setEmail(personneParam.getEmail());
		personne.setSiteweb(personneParam.getSiteweb());
		personne.setDtn(personneParam.getDtn());
		
		iPersonneRepository.save(personne);
		
		model.addAttribute("att_personne", personne);
		model.addAttribute("att_taille", taille);
		model.addAttribute("att_page", page);
		
		return "personne"; // ==> personne.html

	}
	
}

