package springmvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spittr.controller.SpitterController;
import spittr.domain.Spitter;
import spittr.service.ISpitterService;

public class SpitterControllerTest {

	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterController sc = new SpitterController();
		MockMvc mockMvc = standaloneSetup(sc).build();
		
		mockMvc.perform(get("/spitter/register"))
			.andExpect(view().name("registerForm"));
	}
	
	@Test
	public void shouldProcessRegistration() throws Exception {
		Spitter newSpitter = new Spitter(24L, "ladygaga", "123456", "lady", "gaga");
		System.out.println(newSpitter);
		Spitter oldSpitter = new Spitter("ladygaga", "123456", "lady", "gaga");
		System.out.println(oldSpitter);
		ISpitterService spitterService = mock(ISpitterService.class);
		when(spitterService.save(oldSpitter)).thenReturn(newSpitter);
		
		SpitterController sc = new SpitterController(spitterService);
		MockMvc mockMvc = standaloneSetup(sc).build();
		mockMvc.perform(post("/spitter/register")
				.param("firstName", "lady")
				.param("lastName", "gaga")
				.param("username", "ladygaga")
				.param("password", "123456"))
				.andExpect(redirectedUrl("/spitter/ladygaga"));
		verify(spitterService,atLeastOnce()).save(oldSpitter);
	}
}
