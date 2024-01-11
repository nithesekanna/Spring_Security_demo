package com.example.Security_only.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.Security_only.entity.UserData;
import com.example.Security_only.repostory.UserRepo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserData> op=repo.findByName(username);
		
		return op.map(UserInfoUserDetails::new).orElseThrow(()->new UsernameNotFoundException("not a user "+username));
	
	}

}
