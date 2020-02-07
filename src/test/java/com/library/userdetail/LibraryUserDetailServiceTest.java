package com.library.userdetail;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import com.library.admin.repository.AdminRepository;
import com.library.model.Librarian;

@RunWith(SpringRunner.class)
public class LibraryUserDetailServiceTest {

	@TestConfiguration
	static class LibraryUserDetailsServiceTestContextConfiguration {

		@Bean
		public UserDetailsService userDetailsService() {
			return new LibraryUserDetailsService();
		}
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@MockBean
	private AdminRepository adminRepository;

	private Librarian lib;

	@Before
	public void setUp() {
		this.lib = new Librarian("TestId", "Test", "ADMIN", "admin");
		Mockito.when(this.adminRepository.findByUserId(this.lib.getUserId())).thenReturn(this.lib);
	}

	@Test(expected = Test.None.class)
	public void testAddLibrarian() {
		UserDetails user = this.userDetailsService.loadUserByUsername(this.lib.getUserId());
		assertThat(user.getUsername().equals(this.lib.getUserId()));
		assertThat(user.getAuthorities().size() != 0);
		assertThat(user.getPassword() != null);
		assertThat(user.isAccountNonExpired());
		assertThat(user.isAccountNonLocked());
		assertThat(user.isCredentialsNonExpired());
		assertThat(user.isEnabled());
	}
}
