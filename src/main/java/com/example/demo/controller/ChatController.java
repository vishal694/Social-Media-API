package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.example.demo.bean.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat.register")
	@SendTo("/topic/{email}")
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor,
			HttpServletRequest request) {
		headerAccessor.getSessionAttributes().put("username", (String) request.getSession().getAttribute("Email"));
		return chatMessage;
	}

	@MessageMapping("/chat.send")
	@SendTo("/topic/{email}")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
}
