package com.e_commerce.ApiGateway.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController {

	@GetMapping("/fallback/userService")
	public ResponseEntity<String> userServiceFallback() {
		return new ResponseEntity<>("UserService is currently not available. Please try again later.",
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@GetMapping("/fallback/productService")
	public ResponseEntity<String> productServiceFallback() {
		return new ResponseEntity<>("ProductService is currently not available. Please try again later.",
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@GetMapping("/fallback/paymentService")
	public ResponseEntity<String> paymentServiceFallback() {
		return new ResponseEntity<>("PaymentService is currently not available. Please try again later.",
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@GetMapping("fallback/categoryService")
	public ResponseEntity<String> categoryFallback() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("CATEGORYSERVICE is currently unavailable. Please try again later.");
	}

	@GetMapping("/fallback/cartService")
	public ResponseEntity<String> cartServiceFallback() {
		return new ResponseEntity<>("CartService is currently not available. Please try again later.",
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@GetMapping("/fallback/default")
	public ResponseEntity<String> defaultFallback() {
		return new ResponseEntity<>("Service is currently not available. Please try again later.",
				HttpStatus.SERVICE_UNAVAILABLE);
	}
}
