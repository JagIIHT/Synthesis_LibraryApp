package com.library.librarian;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.librarian.repository.LibrarianRepository;
import com.library.model.Book;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LibrarianRepositoryTest {

	@Autowired
	private LibrarianRepository librarianRepository;

	private Book issuedBook;
	private Book returnedBook;

	@Before
	public void setUp() {
		this.issuedBook = new Book(1L, "Java", "Y");
		this.returnedBook = new Book(2L, "C", "N");
		this.librarianRepository.save(this.returnedBook);
	}

	@Test(expected = Test.None.class)
	public void testSaveBook() {
		Book book = this.librarianRepository.save(this.issuedBook);
		assertThat(book.getName().equals(this.issuedBook.getName()));
	}

	@Test(expected = Test.None.class)
	public void testFindById() {
		Book b = this.librarianRepository.save(this.issuedBook);
		Optional<Book> book = this.librarianRepository.findById(b.getBookId());
		assertThat(book.get().getName().equals(b.getName()));
	}

	@Test(expected = Test.None.class)
	public void testFindAll() {
		this.librarianRepository.save(this.issuedBook);
		List<Book> list = (List<Book>) this.librarianRepository.findAll();
		assertThat(list.size() == 2);
		assertThat(list.get(0).getName().equals(this.issuedBook.getName()));
	}

	@Test(expected = Test.None.class)
	public void testFindAllByIssued() {
		this.librarianRepository.save(this.issuedBook);
		List<Book> list = this.librarianRepository.findAllByIssued("Y");
		assertThat(list.get(0).getName().equals(this.issuedBook.getName()));
	}
}
