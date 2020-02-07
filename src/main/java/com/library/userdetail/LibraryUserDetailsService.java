package com.library.userdetail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.library.admin.repository.AdminRepository;
import com.library.model.Librarian;

@Component
public class LibraryUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	/**
	 *
	 * Used for setting the User details from Database to UserDetailsService for
	 * authentication and authorization
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Librarian librarian = this.adminRepository.findByUserId(userId);
		return new UserDetails() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -5803536101737145608L;

			@Override
			public boolean isEnabled() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public String getUsername() {
				return userId;
			}

			@Override
			public String getPassword() {
				return librarian.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
				list.add(new SimpleGrantedAuthority("ROLE_" + librarian.getRole()));
				return list;
			}
		};
	}

}
