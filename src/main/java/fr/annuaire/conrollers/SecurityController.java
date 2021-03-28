package fr.annuaire.conrollers;

import javax.servlet.http.HttpServletRequest;
//import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.annuaire.models.Personne;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

@Controller
public class SecurityController {	
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model) {
		
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
        	String name = auth.getName();
        	System.out.println("name="+ name );
        }*/
        
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String identifiant;
		
		if ( !(principal instanceof UserDetails) ) {
			identifiant = principal.toString();
			return "login"; // ==> login.html
		} else {
			identifiant = ( (UserDetails) principal ).getUsername();
			Personne personne = (Personne) request.getSession().getAttribute("MY_SESSION_PERSONNE");
			if (personne == null) {
				personne = new Personne();
			}
			try {
				personne.setId( Long.parseLong(identifiant) );
				request.getSession().setAttribute("MY_SESSION_PERSONNE", personne);
				return "home"; // ==> home.html
			} catch (NumberFormatException e) {
				model.addAttribute("att_NumberFormatException", "Le nom d'utilisateur ou le mot de passe est incorrect !");
				request.getSession().setAttribute("MY_SESSION_PERSONNE", personne);
				return "login"; // ==> login.html
			}
			
		}
		
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/groupes"; // ==> login.html
	}
	
	@RequestMapping(value="/")
	public String home() {
		return "redirect:/home"; // ==> home.html
	}
	
	@RequestMapping( value="/home")
	public String accueil() {
		return "home"; // ==> home.html
	}
	
	@RequestMapping(value="/403")
	public String accessDenied() {
		return "403"; // ==> 403.html
	}
	
	@RequestMapping(value="/apropos")
	public String apropos() {
		return "apropos"; // ==> apropos.html
	}
	
}