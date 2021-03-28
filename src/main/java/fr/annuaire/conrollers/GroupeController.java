package fr.annuaire.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fr.annuaire.dao.IGroupeRepository;
import fr.annuaire.models.Groupe;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

@Controller
public class GroupeController {
	
	@Autowired
	private IGroupeRepository iGroupeRepository;
	
	@RequestMapping( value="/groupes", method=RequestMethod.GET )
	public String groupes(
			Model model,
			@RequestParam(name="nom", defaultValue="") String nom,
			@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="taille", defaultValue="5") int taille) {
		
		page--;
		
		try {
			@SuppressWarnings("deprecation")
			Page<Groupe> pageGroupes = iGroupeRepository.chercher("%"+nom+"%",new PageRequest(page, taille));
			model.addAttribute("att_nomGroupe", nom);
			if(pageGroupes.isEmpty()) {
				model.addAttribute("att_emptyList", "Le nom du groupe que vous avez entrer n'existe pas !");
			} else {
				model.addAttribute("att_groupeList", pageGroupes);
			}
			int[] pages = new int[pageGroupes.getTotalPages()];
			model.addAttribute("att_pages", pages);
			model.addAttribute("att_taille", taille);
			model.addAttribute("att_page", page);
		} catch (Exception e) {
			model.addAttribute("att_exception", e.getMessage());
		}
		
		return "groupes"; // ==> groupes.html
	}

}
