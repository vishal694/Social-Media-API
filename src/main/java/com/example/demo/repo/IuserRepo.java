package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.User;

public interface IuserRepo extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
