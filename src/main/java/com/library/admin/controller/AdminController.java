package com.library.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.admin.service.AdminService;
import com.library.model.Librarian;
import com.library.model.LibraryResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final String SUCCESS = "SUCCESS";
	@Autowired
	private AdminService adminService;

	/**
	 * 
	 * Returns all the librarians from Database
	 * 
	 * @return
	 */
	@GetMapping(produces = "application/json")
	public LibraryResponse<List<Librarian>> getAllLibrarians() {
		return new LibraryResponse<List<Librarian>>(this.adminService.getAllLibrarians(), SUCCESS);
	}

	/**
	 * 
	 * Used to save a new librarian
	 * 
	 * @param librarian
	 * @return
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	public LibraryResponse<Librarian> addLibrarian(@RequestBody Librarian librarian) {
		return new LibraryResponse<Librarian>(this.adminService.addLibrarian(librarian), SUCCESS);
	}

	/**
	 * 
	 * Deletes the given librarian from Database
	 * 
	 * @param librarian
	 * @return
	 */
	@DeleteMapping(produces = "application/json", consumes = "application/json")
	public LibraryResponse<Librarian> getLibrarian(@RequestBody Librarian librarian) {
		this.adminService.deleteLibrarian(librarian);
		return new LibraryResponse<Librarian>(librarian, SUCCESS);
	}

}
