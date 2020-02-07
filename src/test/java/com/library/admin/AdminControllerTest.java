package com.library.admin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.LibraryApplication;
import com.library.model.Librarian;
import com.library.model.LibraryResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
@WebAppConfiguration
public class AdminControllerTest {

	private MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	private Librarian lib;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		this.lib = new Librarian("TestId", "Test", "TestAdmin", "TestAdmin");
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAllLibrarians() throws Exception {
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.get("/admin")).andReturn();
		String r = result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		LibraryResponse<List<Librarian>> response = this.mapper.readValue(r,  LibraryResponse.class);
		assertThat(response.getStatus().equals("SUCCESS"));
	}
	
	@Test
	public void testAddLibrarian() throws Exception {
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/admin").contentType("application/json").content(this.mapper.writeValueAsString(this.lib))).andReturn();
		String r = result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		LibraryResponse<Librarian> response = this.mapper.readValue(r,  LibraryResponse.class);
		assertThat(response.getStatus().equals("SUCCESS"));
	}
	
	@Test
	public void testDeleteLibrarian() throws Exception {
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.delete("/admin").contentType("application/json").content(this.mapper.writeValueAsString(this.lib))).andReturn();
		String r = result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		LibraryResponse<Librarian> response = this.mapper.readValue(r,  LibraryResponse.class);
		assertThat(response.getStatus().equals("SUCCESS"));
	}

}
