package com.library.admin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.admin.repository.AdminRepository;
import com.library.model.Librarian;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AdminRepositoryTest {

	@Autowired
	private AdminRepository adminRepository;

	private Librarian lib;

	@Before
	public void setUp() {
		this.lib = new Librarian("TestId", "Test", "ADMIN", "admin");
	}

	@Test(expected = Test.None.class)
	public void testAddLibrarian() {
		Librarian lib = this.adminRepository.save(this.lib);
		assertThat(lib.getUserId().equals(this.lib.getUserId()));
	}

	@Test(expected = Test.None.class)
	public void testGetAllLibrarian() {
		this.adminRepository.save(this.lib);
		List<Librarian> list = (List<Librarian>) this.adminRepository.findAll();
		assertThat(list.get(0).getUserId().equals(this.lib.getUserId()));
	}

	@Test(expected = Test.None.class)
	public void testDeleteLibrarian() {
		this.adminRepository.save(this.lib);
		this.adminRepository.delete(this.lib);
	}

	@Test
	public void testFindByUserId() {
		this.adminRepository.save(this.lib);
		Librarian lib = this.adminRepository.findByUserId(this.lib.getUserId());
		assertThat(lib.getUserId().equals(this.lib.getUserId()));
	}
}
