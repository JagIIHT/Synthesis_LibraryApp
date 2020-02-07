package com.library.librarian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.exception.LibraryException;
import com.library.librarian.repository.LibrarianRepository;
import com.library.model.Book;

@Service
public class LibrarianServiceImpl implements LibrarianService {

	@Autowired
	private LibrarianRepository librarianRepository;

	/**
	 * 
	 * Used to add a new book to library
	 * 
	 * @param book
	 * @return
	 */
	@Override
	public Book addBook(Book book) {
		book.setIssued("N");
		return this.librarianRepository.save(book);
	}

	/**
	 * Used to retrieve all the books from library
	 * 
	 * @return
	 */
	@Override
	public List<Book> getAllBooks() {
		return (List<Book>) this.librarianRepository.findAll();
	}

	/**
	 * Used to issue a book to Student from library
	 * 
	 * @param book
	 * @return
	 * @throws LibraryException
	 */
	@Override
	public Book issueBook(Book book) throws LibraryException {
		Optional<Book> b = this.librarianRepository.findById(book.getBookId());
		if (b.isPresent()) {
			if (b.get().getIssued().equals("N")) {
				book.setIssued("Y");
				return this.librarianRepository.save(book);
			} else {
				throw new LibraryException("Book already issued");
			}
		} else {
			throw new LibraryException("Book not available in library");
		}
	}

	/**
	 * Used to return a book from Student to library
	 * 
	 * @param book
	 * @return
	 * @throws LibraryException
	 */
	@Override
	public Book returnBook(Book book) throws LibraryException {
		Optional<Book> b = this.librarianRepository.findById(book.getBookId());
		if (b.isPresent() && b.get().getIssued().equals("Y")) {
			book.setIssued("N");
			return this.librarianRepository.save(book);
		} else {
			throw new LibraryException("Book already returned or doesn't belong to this library");
		}
	}

	/**
	 * Returns all the books which are issued to students
	 * 
	 * @return
	 */
	@Override
	public List<Book> getAllIssuedBooks() {
		return this.librarianRepository.findAllByIssued("Y");
	}

}
