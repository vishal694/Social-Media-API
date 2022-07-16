package com.example.demo.serviceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bean.Category;
import com.example.demo.bean.File;
import com.example.demo.bean.Post;
import com.example.demo.bean.User;
import com.example.demo.exception.FileStorageException;
import com.example.demo.exception.ResourceNotException;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.repo.ICategoryRepo;
import com.example.demo.repo.IFileRepo;
import com.example.demo.repo.IPostRepo;
import com.example.demo.repo.IUserRepo;
import com.example.demo.service.IPostService;

@Service
public class postServiceImpl implements IPostService {

	@Autowired
	private IPostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IUserRepo userRepo;

	@Autowired
	private ICategoryRepo categoryRepo;
	
	@Autowired
	private IFileRepo fileRepo;

//	@Async
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotException("User", "User Id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotException("Category", "Category Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		//file upload content
		
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}


	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotException("post", "post Id", postId));

		post.setPostTitle(postDto.getPostTitle());
		post.setContent(postDto.getContent());
		post.setImagName(postDto.getImagName());
		Post updatePost = this.postRepo.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotException("post", "post Id", postId));
		this.postRepo.deleteById(postId);
	}

//	@Async
	@Override
	public PostResponse getAllPost(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> allPosts = this.postRepo.findAll(pageable);
		List<Post> posts = allPosts.getContent();
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNuber(allPosts.getNumber());
		postResponse.setPageSize(allPosts.getSize());
		postResponse.setTotalElements(allPosts.getTotalPages());
		postResponse.setTotalPages(allPosts.getTotalPages());
		postResponse.setLastPage(allPosts.isLast());
		return postResponse;
	}

//	@Async
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cate = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotException("Category", "Category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cate);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		return postDto;
	}

	@Async
	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotException("User", "User Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDto;
	}

	@Override
	public List<PostDto> searchPosts(String Keyword, String sortDir, Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Post> posts = this.postRepo.findByTitleContaining("%" + Keyword + "%", pageable);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		postDto = (sortDir.equalsIgnoreCase("desc"))
				? postDto.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList())
				: postDto.stream().sorted().collect(Collectors.toList());

		return postDto;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotException("post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	protected  void storeFile(MultipartFile file) {
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		 try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	            File dbFile = new File(fileName, file.getContentType(), file.getBytes());

	             fileRepo.save(dbFile);
	        } catch (IOException ex) {
	            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
	        }
	}
}
