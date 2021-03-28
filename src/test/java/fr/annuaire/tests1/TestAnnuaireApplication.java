package fr.annuaire.tests1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestAnnuaireApplication extends SpringBootTests{

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testEmployee() throws Exception {
		mockMvc.perform(get("/restpersonne")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				/*.andExpect(jsonPath("$.name").value("p1"))
				.andExpect(jsonPath("$.id").value(17028432))
				.andExpect(jsonPath("$.nom").value("BOULAHYA"))
				.andExpect(jsonPath("$.prenomnom").value("Ayoub"))
				.andExpect(jsonPath("$.email").value("b.a@gmail.com"))
				.andExpect(jsonPath("$.siteweb").value("www.ayoub-software.ma"))
				.andExpect(jsonPath("$.dtn").value("2009-12-31"))
				*/
				;
	}
	
}
