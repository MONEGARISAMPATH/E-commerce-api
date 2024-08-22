package com.e_commerce.CategoryService.Clients;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.e_commerce.CategoryService.Dto.ProductDto;

@FeignClient(name = "PRODUCTSERVICE")
public interface ProductFeginClient {
	@GetMapping("/product/{id}")
	ProductDto getProductById(@PathVariable Long id);

}