package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.CategoryDto;
import com.example.demo.service.ICategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/categories")
@Api(tags = "Category", value = "PostCategories", description = "User Post Categories Operations")
public class CategoryController {

	Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private ICategoryService categoryService;

	@PostMapping("/create-category")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = CategoryController.class, responseContainer = "List") })
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		log.debug("Request for create category", categoryDto.getId());
		CategoryDto categories = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categories, HttpStatus.CREATED);

	}

	@PutMapping("/update-category/{id}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = CategoryController.class, responseContainer = "List") })
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
		log.debug("Request for update category", categoryDto.getId());
		CategoryDto categories = this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(categories, HttpStatus.OK);

	}

	@DeleteMapping("/delete-category/{id}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = CategoryController.class, responseContainer = "List") })
	public ResponseEntity<?> deleteCategory(@PathVariable("userId") Integer id) {
		log.debug("Request for delete category", id);
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<>(Map.of("message", "user delete successfully"), HttpStatus.OK);
	}

	@GetMapping("/")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = CategoryController.class, responseContainer = "List") })
	public ResponseEntity<List<CategoryDto>> getCategories() {
		log.debug("Request for get categories");
		return ResponseEntity.ok(this.categoryService.getCategoryes());

	}

	@GetMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = CategoryController.class, responseContainer = "List") })
	public ResponseEntity<CategoryDto> getByUsers(@PathVariable Integer id) {
		log.debug("Request for get category using userId", id);
		return ResponseEntity.ok(this.categoryService.getCategory(id));

	}
}
