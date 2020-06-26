package com.jboss.eagleeye.service;

import com.jboss.eagleeye.model.User;
import com.jboss.eagleeye.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService{
	String ROLE_PREFIX = "ROLE_";
	 @Autowired
	 private UserRepository userRepository;

	 @Override
	 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	     User user = userRepository.findByEmail(email);
	     if (user == null) {
	          throw new UsernameNotFoundException("User not found with Email: " + email);
	     }
		 return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				 mapRolesToAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Long role) {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

		list.add(new SimpleGrantedAuthority(ROLE_PREFIX +role));

		return list;
	}
}
