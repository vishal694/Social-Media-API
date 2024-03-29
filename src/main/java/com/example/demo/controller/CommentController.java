package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.CommentDto;
import com.example.demo.service.ICommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/comments")
@Api(tags = "Comments", value = "UsersPostComments", description = "Comments on friend posts")
public class CommentController {

	Logger log = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private ICommentService commService;

	@PostMapping("/{postId}/{userId}/add-comment")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = CommentController.class, responseContainer = "List") })
	public ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto,
			@PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId) {

		log.debug("Request for a new post comment", userId, postId);
		CommentDto createPost = this.commService.createComments(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(createPost, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete-comment/{userId}/{cId}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = CommentController.class, responseContainer = "List") })
	public ResponseEntity<?> deleteCategory(@PathVariable("userId") Integer userId, @PathVariable("cId") Integer cId) {

		log.debug("Request for delete post comment", userId);
		this.commService.deleteComments(cId, userId);
		return new ResponseEntity<>(Map.of("message", "user delete successfully"), HttpStatus.OK);
	}

}
