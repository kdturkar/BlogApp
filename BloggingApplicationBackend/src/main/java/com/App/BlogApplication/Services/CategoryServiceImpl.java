package com.App.BlogApplication.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.App.BlogApplication.DTO.CategoryDto;
import com.App.BlogApplication.Entities.Category;
import com.App.BlogApplication.Exception.ResourceNotFoundException;
import com.App.BlogApplication.Repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.catDtoToEnt(categoryDto);
		Category savedCategory = this.categoryRepo.save(category);
		return catEntToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
		Category category = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
		category.setCategoryId(categoryDto.getCategoryId());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepo.save(category);
		return catEntToDto(updatedCategory);
	}

	@Override
	public void deleteCategory(Integer catId) {
		Category category = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer catId) {
		Category category = this.categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
		return this.catEntToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = categories.stream().map(category -> this.catEntToDto(category))
				.collect(Collectors.toList());
		return categoryDtos;
	}

	CategoryDto catEntToDto(Category category) {
		CategoryDto categoryDto = this.mapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	Category catDtoToEnt(CategoryDto categoryDto) {
		return this.mapper.map(categoryDto, Category.class);
	}

}
