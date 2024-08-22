package com.e_commerce.CategoryService.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.e_commerce.CategoryService.Dto.CategoryDto;
import com.e_commerce.CategoryService.Entity.Category;
import com.e_commerce.CategoryService.ErrorResponse.ErrorResponse;
import com.e_commerce.CategoryService.Service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	Environment environment;

	@GetMapping("/port")
	public String portNumberStatus() {
		return "CategoryService running port number is :" + " " + environment.getProperty("local.server.port");

	}

	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> allCategories = categoryService.getAllCategories(null);
		return new ResponseEntity<>(allCategories, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
		Optional<CategoryDto> categoryId = categoryService.getCategoryById(id);
		if (categoryId.isPresent()) {
			CategoryDto category = categoryId.get();
			return new ResponseEntity<>(category, HttpStatus.OK);
		} else {
			String errorMessage = "Category not found with ID: " + id;
			logger.error("Error: {} | Status: {} ", errorMessage, HttpStatus.NOT_FOUND);
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
		Category categor = categoryService.createCategory(category);
		return new ResponseEntity<>(categor, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable Long id, @Valid @RequestBody Category categoryUpdate) {
		Optional<Category> categoryId = categoryService.updateCategory(id, categoryUpdate);
		if (categoryId.isPresent()) {
			Category updatedCategory = categoryId.get();
			return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
		} else {
			String errorMessage = "Category not found with ID: " + id;
			logger.error("Error: {} | Status: {} ", errorMessage, HttpStatus.NOT_FOUND);
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
