package fr.annuaire.tests2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import fr.annuaire.conrollers.PersonneController;
import fr.annuaire.dao.IPersonneRepository;
import fr.annuaire.models.Personne;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApplicationTests {
	
	@SuppressWarnings("unused")
	@Autowired
	private PersonneController personncontroller;
	
	@MockBean
	private IPersonneRepository personneRepository;
	
	@Test
	public void affichePersonneTest() {
		Long id = (long) 17028432;
		Personne p = new Personne();
		p.setId(id);
		
		//when(personneRepository.findById(id)).thenReturn( Stream.of(p)).collect(Collectors.toList()));
		
		//assertEquals("personne", personncontroller.affichePersonne(new ModelAndView(), id, 0, 5, new Session()));
	}
	
}
