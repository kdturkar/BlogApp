package com.App.BlogApplication.Services;

import java.util.List;

import com.App.BlogApplication.DTO.CategoryDto;

public interface CategoryService {
	// create update delete get get_all

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);

	void deleteCategory(Integer catId);

	CategoryDto getCategory(Integer catId);

	List<CategoryDto> getAllCategory();

}
