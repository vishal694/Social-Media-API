package com.example.demo.bean;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@ApiModel
public class JwtAuthRequest {

	private String userName;

	private String password;
}
