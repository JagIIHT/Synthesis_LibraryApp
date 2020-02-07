package com.library.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.model.Librarian;

@Service
public interface AdminService {

	/**
	 * Used to save a new librarian
	 * 
	 * @param librarian
	 * @return
	 */
	Librarian addLibrarian(Librarian librarian);

	/**
	 * Returns all the librarians from Database
	 * 
	 * @return
	 */
	List<Librarian> getAllLibrarians();

	/**
	 * Deletes the given librarian from Database
	 * 
	 * @param librarian
	 */
	void deleteLibrarian(Librarian librarian);

}
