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
import com.example.demo.service.IcommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	@Autowired
	private IcommentService commService;

	@PostMapping("/{postId}/{userId}/add-comment")
	public ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto,
			@PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId) {
		CommentDto createPost = this.commService.createComments(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(createPost, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete-comment/{userId}/{cId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("userId") Integer userId, @PathVariable("cId") Integer cId) {
		this.commService.deleteComments(cId, userId);
		return new ResponseEntity<>(Map.of("message", "user delete successfully"), HttpStatus.OK);
	}

}
