package com.example.demo.service;

import java.util.List;

import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;

public interface IpostService {

	PostDto createPost(PostDto postDto,Integer userId ,Integer categoryId);
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageSize ,Integer pageNumber,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer UserId);
	
	List<PostDto> searchPosts(String Keyword,String sortdir,Integer pageNumber,Integer pageSize);
}
