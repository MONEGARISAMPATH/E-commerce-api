package com.e_commerce.UserService.Controller;

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

import com.e_commerce.UserService.Entity.ErrorResponse;
import com.e_commerce.UserService.Entity.User;
import com.e_commerce.UserService.Service.UserService;
import com.e_commerce.UserService.UserDto.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	Environment environment;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/port")
	public String portNumberStatus() {
		return "UserService running port number is :" + " " + environment.getProperty("local.server.port");

	}
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {

		Optional<User> allUsersId = userService.getUserById(id);

		if (allUsersId.isPresent()) {
			User allUsers = allUsersId.get();
			return new ResponseEntity<>(allUsers, HttpStatus.OK);
		} else {
			String errorMessage = "User not found with ID: " + id;
			logger.error("Error: {} | Status: {}", errorMessage, HttpStatus.NOT_FOUND);
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/orderStatus/{id}/{orderId}")
	public ResponseEntity<?> getByUserId(@PathVariable Long id, @PathVariable Long orderId) {
		Optional<UserDto> optionalUser = userService.getByUserId(id, orderId);
		if (optionalUser.isPresent()) {
			UserDto user = optionalUser.get();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			String errorMessage = "User not found with ID: " + id;

			logger.error("Error: {} | Status: {}", errorMessage, HttpStatus.NOT_FOUND);
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User users = userService.createUser(user);
		return new ResponseEntity<>(users, HttpStatus.OK);
		// return ResponseEntity.ok(userService.createUser(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User updateUser) {
		Optional<User> excistUser = userService.updateUser(id, updateUser);
		if (excistUser.isPresent()) {
			User userUpdated = excistUser.get();
			return new ResponseEntity<>(userUpdated, HttpStatus.OK);
		} else {
			String errorMessage = "User not found with ID: " + id;
			logger.error("Error: {} | Status: {} ", errorMessage, HttpStatus.NOT_FOUND);
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(true);
	}

}
