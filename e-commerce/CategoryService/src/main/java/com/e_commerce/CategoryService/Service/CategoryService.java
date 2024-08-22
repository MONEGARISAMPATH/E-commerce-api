package com.e_commerce.CategoryService.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.CategoryService.Clients.ProductFeginClient;
import com.e_commerce.CategoryService.Dto.CategoryDto;
import com.e_commerce.CategoryService.Dto.ProductDto;
import com.e_commerce.CategoryService.Entity.Category;
import com.e_commerce.CategoryService.Repository.CategoryRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class CategoryService {
	 private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
	@Autowired
	ProductFeginClient productFeginClient;

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getAllCategories(Category category) {
		return categoryRepository.findAll();
	}
	
	@CircuitBreaker(name="categoryServiceProductCircuitBreaker",fallbackMethod="productFallBackMethod")
	public Optional<CategoryDto> getCategoryById(Long id) {
		Category category = categoryRepository.findById(id).orElse(null);
		if(category!=null) {	
		List<ProductDto> products=new ArrayList<>();
		for(Long productId: category.getProductId()) {
			ProductDto product=productFeginClient.getProductById(productId);
			products.add(product);
		}
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		categoryDto.setSlug(category.getSlug());
		categoryDto.setCreatedAt(category.getCreatedAt());
		categoryDto.setUpdatedAt(category.getUpdatedAt());
		categoryDto.setProductDto(products);
		categoryDto.setStatus("Successfully fetched products..");
		categoryDto.setProductMessage("Products are available for this category");
		return Optional.of(categoryDto);
	}else {
		log.warn("catgeory with ID {} not found", id);
		return Optional.empty();
	}
	}
	public Optional<CategoryDto> productFallBackMethod(Long id,Exception e) {
		log.warn("Fallback method invoked due to: {}", e.getMessage());
		Optional<Category> categoryId = categoryRepository.findById(id);
		if(categoryId.isPresent()) {
			Category category=categoryId.get();
			CategoryDto categoryDto=new CategoryDto();
			categoryDto.setId(category.getId());
			categoryDto.setName(category.getName());
			categoryDto.setSlug(category.getSlug());
			categoryDto.setCreatedAt(category.getCreatedAt());
			categoryDto.setUpdatedAt(category.getUpdatedAt());
			categoryDto.setStatus("Product Service is unavailable or down please try again later..");
			categoryDto.setProductMessage("No products available for this category at the moment.");
			return Optional.of(categoryDto);
		}else {
			
				log.warn("Product with ID {} not found during fallback");
				return Optional.empty();
			}
		}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);

	}

	public Optional<Category> updateCategory(Long id, Category categoryUpadte) {
		Optional<Category> categoryId = categoryRepository.findById(id);
		if (categoryId.isPresent()) {
			Category category = categoryId.get();
			category.setName(categoryUpadte.getName());
			category.setSlug(categoryUpadte.getSlug());
			category.setProductId(categoryUpadte.getProductId());
			categoryRepository.save(category);
			return Optional.of(category);

		} else {
			return Optional.empty();
		}
	}

	public boolean deleteCategory(Long id) {
		categoryRepository.deleteById(id);
		return true;

	}

}
