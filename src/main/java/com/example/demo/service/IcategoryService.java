package com.example.demo.service;

import java.util.List;

import com.example.demo.payloads.CategoryDto;

public interface IcategoryService {
	
	CategoryDto createCategory(CategoryDto category);
	
	CategoryDto updateCategory(CategoryDto category,int id);
	
	void deleteCategory(int id);
	
	CategoryDto getCategory(int id);
	
	List<CategoryDto> getCategoryes();
}
