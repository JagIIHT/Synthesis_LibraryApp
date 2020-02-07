package com.library.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.admin.repository.AdminRepository;
import com.library.model.Librarian;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	/**
	 * Used to save a new librarian
	 * 
	 * @param librarian
	 * @return
	 */
	@Override
	public Librarian addLibrarian(Librarian librarian) {
		return this.adminRepository.save(librarian);
	}

	/**
	 * Deletes the given librarian from Database
	 * 
	 * @param librarian
	 */
	@Override
	public void deleteLibrarian(Librarian librarian) {
		this.adminRepository.delete(librarian);
	}

	/**
	 * Returns all the librarians from Database
	 * 
	 * @return
	 */
	@Override
	public List<Librarian> getAllLibrarians() {
		return (List<Librarian>) this.adminRepository.findAll();
	}
}
