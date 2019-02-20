package springmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import spittr.controller.HomeController;

public class HelloControllerTest {

	@Test
	public void testHomePage() throws Exception {
		HomeController hc = new HomeController();
		MockMvc mockMvc = standaloneSetup(hc).build();
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}
	
	@Test
	public void test1() {
		System.out.println(HttpStatus.NOT_FOUND);
	}
}
