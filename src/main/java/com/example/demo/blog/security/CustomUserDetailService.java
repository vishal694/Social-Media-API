package com.example.demo.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.repo.IUserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private IUserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepo.findByEmail(username);
		return user;
	}
	
}
