package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Category;
import com.example.demo.exception.ResourceNotException;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.repo.ICategoryRepo;
import com.example.demo.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto category) {

		Category categ = modelMapper.map(category, Category.class);
		Category addCategory = this.categoryRepo.save(categ);
		return this.modelMapper.map(addCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, int id) {
		Category categ = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotException("category", "categoryId", id));
		categ.setCategoryTitle(category.getCategoryTitle());
		categ.setCategoryDisc(category.getCategoryDisc());
		Category updateCategory = this.categoryRepo.save(categ);
		return this.modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int id) {
		this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotException("category", "categoryId", id));
		this.categoryRepo.deleteById(id);
	}

	@Override
	public CategoryDto getCategory(int id) {
		Category cat = this.categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotException("category", "categoryId", id));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategoryes() {
		List<Category> cateList = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = cateList.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return categoryDtos;
	}

}
