package com.example.demo.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Comment;
import com.example.demo.bean.Post;
import com.example.demo.bean.User;
import com.example.demo.exception.ResourceNotException;
import com.example.demo.payloads.CommentDto;
import com.example.demo.repo.IcommentRepo;
import com.example.demo.repo.IpostRepo;
import com.example.demo.repo.IuserRepo;
import com.example.demo.service.IcommentService;

@Service
public class CommentServiceImpl implements IcommentService {

	@Autowired
	private IpostRepo postRepo;

	@Autowired
	private IcommentRepo commentRepo;

	@Autowired
	private IuserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComments(CommentDto commentDto, Integer postId, Integer userId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotException("Post", "Post Id", postId));
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotException("user", "user Id", userId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUserComment(user);
		Comment savedComments = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComments, CommentDto.class);
	}

	@Override
	public void deleteComments(Integer cId, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotException("user", "user Id", userId));
		Comment comment = this.commentRepo.findById(cId)
				.orElseThrow(() -> new ResourceNotException("Comment", "Comment Id", cId));
		
		this.commentRepo.delete(comment);
	}

}
