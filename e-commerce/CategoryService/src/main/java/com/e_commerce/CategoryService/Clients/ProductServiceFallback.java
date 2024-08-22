package com.e_commerce.CategoryService.Clients;

import org.springframework.stereotype.Component;

import com.e_commerce.CategoryService.Dto.ProductDto;

@Component
public class ProductServiceFallback implements ProductFeginClient  {

	@Override
	public ProductDto getProductById(Long id) {
		ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName("Product Service is currently unavailable" );
        return productDto;
		
	}

}
