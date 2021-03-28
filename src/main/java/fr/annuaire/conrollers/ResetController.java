package fr.annuaire.conrollers;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResetController {
	
	@RequestMapping(value="/forgotPassword")
	public String forgotPassword() {
		System.out.println("/forgotPassword");
		return "forgotPassword"; // ==> "forgotPassword.html" 
	}
	
}
