package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.booktaxi.dao.UserEntityDao;
import com.app.booktaxi.entity.UserEntity;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	// dep
	@Autowired
	private UserEntityDao dao;
	// private OwnerDao dao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = dao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid email...."));
		// user :persistent
		return new CustomUserDetails(user);
	}

//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Owner user = dao.findByEmail(email)
//				.orElseThrow(() ->
//				new UsernameNotFoundException("Invalid email...."));
//		//user :persistent
//		return new CustomUserDetails(user);
//	}

}
