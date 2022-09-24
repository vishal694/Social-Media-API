package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UsersRequest")
@NoArgsConstructor
@Getter
@Setter
@ApiModel
public class UsersRequest {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String requestId;

	private boolean requestFlag;
	
	private String toEmail;
	
	private String fromEmail;
	
	private int toRequestId;
	
	private int fromRequesId;
	
}
