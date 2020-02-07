package com.library.librarian;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.exception.LibraryException;
import com.library.librarian.repository.LibrarianRepository;
import com.library.librarian.service.LibrarianService;
import com.library.librarian.service.LibrarianServiceImpl;
import com.library.model.Book;

@RunWith(SpringRunner.class)
public class LibrarianServiceTest {

	@TestConfiguration
	static class LibrarianServiceTestContextConfiguration {

		@Bean
		public LibrarianService librarianService() {
			return new LibrarianServiceImpl();
		}
	}

	@MockBean
	private LibrarianRepository librarianRepository;

	@Autowired
	private LibrarianService librarianService;

	private Book book;

	@Before
	public void setUp() {
		List<Book> allList = Arrays.asList(new Book(1L, "Java", "Y"), new Book(2L, "C", "N"));
		List<Book> issuedList = Arrays.asList(new Book(1L, "Java", "Y"));
		this.book = new Book(1L, "Java", "Y");
		Mockito.when(this.librarianRepository.findAll()).thenReturn(allList);
		Mockito.when(this.librarianRepository.save(this.book)).thenReturn(this.book);
		Mockito.when(this.librarianRepository.findAllByIssued("Y")).thenReturn(issuedList);
	}

	@Test
	public void testAddBook() {
		Book b = this.librarianService.addBook(this.book);
		assertThat(b.getName().equals(this.book.getName()));
	}

	@Test
	public void testGetAllBooks() {
		List<Book> list = this.librarianService.getAllBooks();
		assertThat(list.size() == 2);
		assertThat(list.get(0).getName().equals("Java"));
		assertThat(list.get(1).getName().equals("C"));
	}

	@Test
	public void testGetAllIssuedBooks() {
		List<Book> list = this.librarianService.getAllIssuedBooks();
		assertThat(list.size() == 1);
		assertThat(list.get(0).getName().equals("Java"));
	}

	@Test
	public void testIssueBook_Success() throws LibraryException {
		Mockito.when(this.librarianRepository.findById(1L)).thenReturn(Optional.of(new Book(1L, "Java", "N")));
		Book book = new Book(1L, "Java", "N");
		Mockito.when(this.librarianRepository.save(book)).thenReturn(new Book(1L, "Java", "Y"));
		Book b = this.librarianService.issueBook(book);
		assertThat(b.getIssued().equals("Y"));
	}

	@Test(expected = LibraryException.class)
	public void testIssueBook_Failure1() throws LibraryException {
		Mockito.when(this.librarianRepository.findById(1L)).thenReturn(Optional.of(new Book(1L, "Java", "Y")));
		this.librarianService.issueBook(new Book(1L, "Java", "N"));
	}

	@Test(expected = LibraryException.class)
	public void testIssueBook_Failure2() throws LibraryException {
		Mockito.when(this.librarianRepository.findById(1L)).thenReturn(Optional.empty());
		this.librarianService.issueBook(new Book(1L, "Java", "N"));
	}

	@Test
	public void testReturnBook_Success() throws LibraryException {
		Mockito.when(this.librarianRepository.findById(1L)).thenReturn(Optional.of(new Book(1L, "Java", "Y")));
		Mockito.when(this.librarianRepository.save(this.book)).thenReturn(new Book(1L, "Java", "N"));
		Book b = this.librarianService.returnBook(this.book);
		assertThat(b.getIssued().equals("N"));

	}

	@Test(expected = LibraryException.class)
	public void testReturnBook_Failure1() throws LibraryException {
		Mockito.when(this.librarianRepository.findById(1L)).thenReturn(Optional.of(new Book(1L, "Java", "N")));
		this.librarianService.returnBook(new Book(1L, "Java", "Y"));
	}

	@Test(expected = LibraryException.class)
	public void testReturnBook_Failure2() throws LibraryException {
		Mockito.when(this.librarianRepository.findById(1L)).thenReturn(Optional.empty());
		this.librarianService.returnBook(new Book(1L, "Java", "Y"));
	}
}
