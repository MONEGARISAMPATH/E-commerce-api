package com.e_commerce.CategoryService.ErrorResponse;

import com.e_commerce.CategoryService.Dto.ProductDto;
import com.e_commerce.CategoryService.Entity.Category;

public class CategoryReponse {
	private ProductDto productDto;
	private Category category;

	public CategoryReponse() {

	}

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
