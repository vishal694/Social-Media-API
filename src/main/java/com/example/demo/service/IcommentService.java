package com.example.demo.service;

import com.example.demo.payloads.CommentDto;

public interface IcommentService {
	
	CommentDto createComments(CommentDto commentDto,Integer postId,Integer userId);
	
	void deleteComments(Integer cId,Integer userId);
}
