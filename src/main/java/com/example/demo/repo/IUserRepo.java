package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.bean.User;

public interface IUserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	@Query(value = "SELECT R.NAME FROM USERS U,USER_ROLES S ,ROLE R WHERE U.EMAIL= :email AND U.ID = S.USER AND S.ROLE = R.ID", nativeQuery = true)
	String findRoleByEmail(String email);
	
	@Query(value = "SELECT USER_NAME FROM USERS WHERE USER_NAME = :name AND EMAIL <> :email ",nativeQuery = true)
	List<String> findName(String name,String email);
}
