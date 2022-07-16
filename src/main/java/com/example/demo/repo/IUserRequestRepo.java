package com.example.demo.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.bean.UsersRequest;

public interface IUserRequestRepo extends JpaRepository<UsersRequest, Integer> {

	@Query(value = "SELECT ID FROM USERS WHERE EMAIL = :email", nativeQuery = true)
	int findIdByEmail(String email);

	@Query(value = "SELECT U.USER_NAME,U.ABOUT FROM USERS_REQUEST R ,USERS U WHERE R.FROM_EMAIL =:email AND R.TO_REQUEST_ID = U.ID", nativeQuery = true)
	List<String> getUserRequests(String email);

	@Query(value = "SELECT U.USER_NAME,U.ABOUT FROM USERS_REQUEST R ,USERS U WHERE R.TO_EMAIL =:email AND R.FROM_REQUES_ID = U.ID", nativeQuery = true)
	List<String> getSendUserRequests(String email);

	@Query(value = "SELECT IF(count(*) > 0, \"TRUE\", \"FALSE\") AS  OUTPUT FROM USERS_REQUEST  WHERE FROM_EMAIL =:fromEmail AND TO_EMAIL =:toEmail", nativeQuery = true)
	String vaildateRequestedOrNot(String fromEmail, String toEmail);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE  USERS_REQUEST SET REQUEST_FLAG = TRUE  WHERE TO_EMAIL =:toEmail AND  FROM_EMAIL =:fromEmail", nativeQuery = true)
	Integer acceptRequest(String fromEmail,String toEmail);
}
