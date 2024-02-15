package com.app.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.booktaxi.entity.UserEntity;

public class CustomUserDetails implements UserDetails {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserEntity user;
	
	//private Owner user;

	public CustomUserDetails(UserEntity user) {
		super();
		this.user = user;
	}
	
//	public CustomUserDetails(Owner user) {
//		super();
//		this.user = user;
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of
				(new SimpleGrantedAuthority(user.getRole().toString()));
	}
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of
//				(new SimpleGrantedAuthority("owner"));
//	}


	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
