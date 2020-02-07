package com.library.librarian.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Book;

@Repository
public interface LibrarianRepository extends CrudRepository<Book, Long> {

	
	/**
	 * Based on issued (issued = "Y") it will return issued books or (issued = "N") available books in library.
	 * 
	 * @param issued
	 * @return
	 */
	public List<Book> findAllByIssued(String issued);
}
