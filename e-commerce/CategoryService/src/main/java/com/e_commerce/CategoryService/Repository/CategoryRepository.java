package com.e_commerce.CategoryService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.CategoryService.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	

}
