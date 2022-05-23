package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.commons.AppConstants;
import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.service.IFileService;
import com.example.demo.service.IPostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private IPostService postService;

	@Autowired
	private IFileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/{userId}/{categoryId}/create-post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.ACCEPTED);

	}

	@GetMapping("user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId) {

		List<PostDto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	@GetMapping("category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<PostResponse> getPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORTED_BY, required = false) String sortBy,
			@RequestParam(value = "sortdir", defaultValue = AppConstants.SORTED_DIR, required = false) String sortDir) {
		PostResponse posts = this.postService.getAllPost(pageSize, pageNumber, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getByPost(@PathVariable Integer id) {
		PostDto postDto = this.postService.getPostById(id);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

	@DeleteMapping("/{id}/delete-post")
	public ApiResponse deletePost(@PathVariable Integer id) {
		this.postService.deletePost(id);
		return new ApiResponse("post is successfully deleted", true);
	}

	@PutMapping("/{id}/update-post")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer id) {
		PostDto updatePost = this.postService.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	@GetMapping("/search-post/{title}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String title,
			@RequestParam(value = "sortdir", defaultValue = "ASC", required = false) String sortDir,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		List<PostDto> result = this.postService.searchPosts(title, sortDir, pageNumber, pageSize);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);

	}

	@PostMapping("/image-upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId)
			throws IOException {
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImagName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	@GetMapping(value = "{imageName}/{postId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
