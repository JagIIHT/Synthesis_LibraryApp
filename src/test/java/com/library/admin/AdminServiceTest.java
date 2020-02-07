package com.library.admin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.admin.repository.AdminRepository;
import com.library.admin.service.AdminService;
import com.library.admin.service.AdminServiceImpl;
import com.library.model.Librarian;

@RunWith(SpringRunner.class)
public class AdminServiceTest {

	@TestConfiguration
	static class AdminServiceTestContextConfiguration {

		@Bean
		public AdminService adminService() {
			return new AdminServiceImpl();
		}
	}

	@Autowired
	private AdminService adminService;

	@MockBean
	private AdminRepository adminRepository;

	private Librarian lib;

	@Before
	public void setUp() {
		this.lib = new Librarian("TestId", "Test", "ADMIN", "admin");
		List<Librarian> list = new ArrayList<Librarian>();
		list.add(this.lib);
		Mockito.when(this.adminRepository.save(this.lib)).thenReturn(this.lib);
		Mockito.when(this.adminRepository.findAll()).thenReturn(list);
	}

	@Test(expected = Test.None.class)
	public void testAddLibrarian() {
		Librarian lib = this.adminService.addLibrarian(this.lib);
		assertThat(lib.getUserId().equals(this.lib.getUserId()));
	}

	@Test(expected = Test.None.class)
	public void testGetAllLibrarian() {
		List<Librarian> list = this.adminService.getAllLibrarians();
		assertThat(list.get(0).getUserId().equals(this.lib.getUserId()));
	}

	@Test(expected = Test.None.class)
	public void testDeleteLibrarian() {
		this.adminService.deleteLibrarian(this.lib);
	}

}
