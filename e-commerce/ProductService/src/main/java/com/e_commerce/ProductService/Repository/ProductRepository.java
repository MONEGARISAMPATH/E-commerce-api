package com.e_commerce.ProductService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.ProductService.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
