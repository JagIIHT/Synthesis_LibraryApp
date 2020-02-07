package com.library.admin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Librarian;

@Repository
public interface AdminRepository extends CrudRepository<Librarian, Long> {

	/**
	 * Finds a librarian with the provided userId
	 * 
	 * @param userId
	 * @return
	 */
	public Librarian findByUserId(String userId);
}
