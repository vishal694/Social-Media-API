package com.example.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {

	private String requestId;

	private boolean requestFlag;

	private String toEmail;

	private String fromEmail;
	
	private int toRequestId;

	private int fromRequesId;

}
