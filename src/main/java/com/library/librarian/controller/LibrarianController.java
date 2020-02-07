package com.library.librarian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.exception.LibraryException;
import com.library.librarian.service.LibrarianService;
import com.library.model.Book;
import com.library.model.LibraryResponse;

@RestController
@RequestMapping("/book")
public class LibrarianController {

	private final static String SUCCESS = "SUCCESS";
	@Autowired
	private LibrarianService librarianService;

	/**
	 * 
	 * Used to retrieve all the books from library
	 * 
	 * @return
	 */
	@GetMapping(produces = "application/json")
	public LibraryResponse<List<Book>> getAllBooks() {
		return new LibraryResponse<List<Book>>(this.librarianService.getAllBooks(), SUCCESS);
	}

	/**
	 * Used to add a new book to library
	 * 
	 * @param book
	 * @return
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public LibraryResponse<Book> addBook(@RequestBody Book book) {
		return new LibraryResponse<Book>(this.librarianService.addBook(book), SUCCESS);
	}

	/**
	 * Used to issue a book to Student from library
	 * 
	 * @param book
	 * @return
	 * @throws LibraryException
	 */
	@PutMapping(value = "/issue", produces = "application/json", consumes = "application/json")
	public LibraryResponse<Book> issueBook(@RequestBody Book book) throws LibraryException {
		return new LibraryResponse<Book>(this.librarianService.issueBook(book), "Book Issued");
	}

	/**
	 * 
	 * Used to return a book from Student to library
	 * 
	 * @param book
	 * @return
	 * @throws LibraryException
	 */
	@PutMapping(value = "/return", produces = "application/json", consumes = "application/json")
	public LibraryResponse<Book> returnBook(@RequestBody Book book) throws LibraryException {
		return new LibraryResponse<Book>(this.librarianService.returnBook(book), "Book Returned");
	}

	/**
	 * 
	 * Returns all the books which are issued to students
	 * 
	 * @return
	 */
	@GetMapping(value = "/issued", produces = "application/json")
	public LibraryResponse<List<Book>> getAllIssuedBooks() {
		return new LibraryResponse<List<Book>>(this.librarianService.getAllIssuedBooks(), SUCCESS);
	}

}
