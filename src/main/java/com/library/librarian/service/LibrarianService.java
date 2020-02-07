package com.library.librarian.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.exception.LibraryException;
import com.library.model.Book;

@Service
public interface LibrarianService {

	/**
	 * 
	 * Used to add a new book to library
	 * 
	 * @param book
	 * @return
	 */
	Book addBook(Book book);

	/**
	 * Used to retrieve all the books from library
	 * 
	 * @return
	 */
	List<Book> getAllBooks();

	/**
	 * Used to issue a book to Student from library
	 * 
	 * @param book
	 * @return
	 * @throws LibraryException
	 */
	Book issueBook(Book book) throws LibraryException;

	/**
	 * Used to return a book from Student to library
	 * 
	 * @param book
	 * @return
	 * @throws LibraryException
	 */
	Book returnBook(Book book) throws LibraryException;

	/**
	 * Returns all the books which are issued to students
	 * 
	 * @return
	 */
	List<Book> getAllIssuedBooks();

}
