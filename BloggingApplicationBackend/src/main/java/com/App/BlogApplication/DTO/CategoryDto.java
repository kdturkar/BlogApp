package com.App.BlogApplication.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	
	@NotNull
	@NotEmpty
	@Size(min = 3,max = 10,message = "Category title must have min 3 and max 10 char")
	private String categoryTitle;
	
	@NotNull
	@NotEmpty
	private String categoryDescription;
}
