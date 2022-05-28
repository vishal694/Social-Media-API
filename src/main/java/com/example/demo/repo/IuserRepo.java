package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.User;

public interface IUserRepo extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	
}
