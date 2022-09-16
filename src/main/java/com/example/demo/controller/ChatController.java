package com.example.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.bean.ChatMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(tags = "chat", value = "userChat", description = "Users chats to friends ")
public class ChatController {

	Logger log = LoggerFactory.getLogger(ChatController.class);

	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ChatController.class, responseContainer = "List") })
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		log.debug("Request for chat register ");
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ChatController.class, responseContainer = "List") })
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		log.debug("Request for send message");
		return chatMessage;
	}

}
