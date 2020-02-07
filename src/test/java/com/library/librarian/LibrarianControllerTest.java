package com.library.librarian;

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
import com.library.librarian.repository.LibrarianRepository;
import com.library.model.Book;
import com.library.model.LibraryResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
@WebAppConfiguration
public class LibrarianControllerTest {

	private MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	private LibrarianRepository librarianRepository;

	private Book book;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		this.book = new Book(1l, "Java", "N");
		this.librarianRepository.save(this.book);
		this.book = new Book(2l, "C", "Y");
		this.librarianRepository.save(this.book);
		this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAllBook() throws Exception {
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.get("/book")).andReturn();
		String r = result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		LibraryResponse<List<Book>> response = this.mapper.readValue(r, LibraryResponse.class);
		assertThat(response.getStatus().equals("SUCCESS"));
	}

	@Test
	public void testAddBook() throws Exception {
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/book").contentType("application/json")
				.content(this.mapper.writeValueAsString(this.book))).andReturn();
		String r = result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		LibraryResponse<Book> response = this.mapper.readValue(r, LibraryResponse.class);
		assertThat(response.getStatus().equals("SUCCESS"));
	}
	
	@Test
	public void testGetAllIssuedBook() throws Exception {
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.get("/book/issued")).andReturn();
		String r = result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		LibraryResponse<List<Book>> response = this.mapper.readValue(r, LibraryResponse.class);
		assertThat(response.getStatus().equals("SUCCESS"));
	}
	
	@Test
	public void testIssueBook() throws Exception {
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.put("/book/issue").contentType("application/json")
				.content(this.mapper.writeValueAsString(this.book))).andReturn();
		String r = result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		LibraryResponse<List<Book>> response = this.mapper.readValue(r, LibraryResponse.class);
		assertThat(response.getStatus().equals("SUCCESS"));
	}
	
	@Test
	public void testReturnBook() throws Exception {
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.put("/book/return").contentType("application/json")
				.content(this.mapper.writeValueAsString(this.book))).andReturn();
		String r = result.getResponse().getContentAsString();
		@SuppressWarnings("unchecked")
		LibraryResponse<List<Book>> response = this.mapper.readValue(r, LibraryResponse.class);
		assertThat(response.getStatus().equals("SUCCESS"));
	}

}
