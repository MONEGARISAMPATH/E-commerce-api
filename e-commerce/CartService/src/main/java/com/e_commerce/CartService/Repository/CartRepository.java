package com.e_commerce.CartService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.CartService.Entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

}
