package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.payloads.UsersTempDto;

public interface IUserTempRepo extends JpaRepository<UsersTempDto, Integer> {

	@Query(value = "SELECT PASSWORD FROM USER_TEMP WHERE EMAIL = :email ",nativeQuery = true)
	String findPasswordUsingEmail(String email);
}
