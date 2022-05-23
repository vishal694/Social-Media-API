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

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping("/create-category")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto categories = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categories,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update-category/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer id){
		CategoryDto categories = this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(categories,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete-category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("userId") Integer id){
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<>(Map.of("message","user delete successfully"),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		return ResponseEntity.ok(this.categoryService.getCategoryes());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getByUsers(@PathVariable Integer id){
		return ResponseEntity.ok(this.categoryService.getCategory(id));
		
	}
}
