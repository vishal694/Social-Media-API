package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Entity
@Data
@ApiModel
public class Role {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String name;
}
