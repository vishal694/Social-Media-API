package com.example.demo.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChatMessage {
	private String content;
	private String sender;
	private String receiver;
}
