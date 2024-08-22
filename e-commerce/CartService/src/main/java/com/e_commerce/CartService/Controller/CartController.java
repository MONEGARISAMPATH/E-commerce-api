package com.e_commerce.CartService.Controller;

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

import com.e_commerce.CartService.Entity.Cart;
import com.e_commerce.CartService.Responses.ErrorResponse;
import com.e_commerce.CartService.Responses.ResponseCartDelete;
import com.e_commerce.CartService.Service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	private static final Logger log = LoggerFactory.getLogger(CartService.class);

	@Autowired
	CartService cartService;
	
	@Autowired
	Environment environment;

	public CartController(CartService cartService) {

		this.cartService = cartService;
		
	}
	
	@GetMapping("/port")
	public String portNumberStatus() {
		return "CartService running port number is :" + " " + environment.getProperty("local.server.port");

	}

	@GetMapping("/")
	public ResponseEntity<List<Cart>> getAllCartList() {
		List<Cart> allCartList = cartService.getAllCartList();
		return new ResponseEntity<>(allCartList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCartById(@PathVariable Long id) {
		Optional<Cart> cartId = cartService.getcartById(id);
		if (cartId.isPresent()) {
			Cart allCarts = cartId.get();
			return new ResponseEntity<>(allCarts, HttpStatus.OK);
		} else {
			String errorMessage = " Cart not found: " + id;
			log.error("Error: {}  | Status: {}", errorMessage, HttpStatus.NOT_FOUND);
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<Cart> createCart(@Valid @RequestBody Cart cart) {
		Cart addCart = cartService.createCart(cart);
		return new ResponseEntity<>(addCart, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCart(@PathVariable Long id, @Valid @RequestBody Cart cart) {
		Optional<Cart> cartId = cartService.updateCart(id, cart);
		if (cartId.isPresent()) {
			Cart updateCart = cartId.get();
			return new ResponseEntity<>(updateCart, HttpStatus.OK);
		} else {
			String errorMessage = "Cart not found with ID: " + id;
			log.error("Error: {} | Status: {} ", errorMessage, HttpStatus.NOT_FOUND);
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseCartDelete> deleteById(@PathVariable Long id) {
		cartService.deleteCart(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
