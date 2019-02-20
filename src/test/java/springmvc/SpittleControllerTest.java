package springmvc;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.controller.SpittleController;
import spittr.domain.Spittle;
import spittr.service.ISpittleService;

public class SpittleControllerTest {
	
	@Test
	public void testSpittlePathvariable() throws Exception {
		Spittle expectedSpittle = new Spittle("Hello", new Date());
		ISpittleService spittleService = mock(ISpittleService.class);
		when(spittleService.getSpittle(12345)).thenReturn(expectedSpittle);
		
		SpittleController sc = new SpittleController(spittleService);
		MockMvc mockMvc = standaloneSetup(sc).build();
		mockMvc.perform(get("/spittles/12345"))
			.andExpect(view().name("spittle"))
			.andExpect(model().attributeExists("spittle"))
			.andExpect(model().attribute("spittle", expectedSpittle));
	}
	
	@Test
	public void shouldShowPagedSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittles(50);
		ISpittleService spittleService = mock(ISpittleService.class);
		when(spittleService.getSpittles(238900, 50)).thenReturn(expectedSpittles);
		
		SpittleController sc = new SpittleController(spittleService);
		MockMvc mockMvc = standaloneSetup(sc).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittles/page?max=238900&count=50"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittles"))
			.andExpect(model().attribute("spittles", hasItems(expectedSpittles.toArray())));
	}
	
	@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittles(20);
		ISpittleService spittleService = mock(ISpittleService.class);
		when(spittleService.getSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);
		
		SpittleController sc = new SpittleController(spittleService);
		MockMvc mockMvc = standaloneSetup(sc).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittles"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittles"))
			.andExpect(model().attribute("spittles", hasItems(expectedSpittles.toArray())));
	}
	
	private List<Spittle> createSpittles(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for(int i = 0;i < spittles.size();i++) {
			spittles.add(new Spittle("Spittle "+i, new Date()));
		}
		return spittles;
	}
}
