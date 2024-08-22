package com.e_commerce.UserService.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "name cannot be empty:")
	@Size(min = 2, max = 20, message = "firstName must be in between 2 to 20 characters")
	private String firstName;

	@NotBlank(message = "name cannot be empty:")
	@Size(min = 2, max = 20, message = "LastName must be in between 2 to 20 characters")
	private String lastName;

	@NotNull(message = "Email is Mandatory:")
	@Email
	private String email;

	@NotBlank(message = "phone is mandatory")
	@Size(min = 10, max = 15, message = "Phone Number must be in between 10 to 15 digits")
	@Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number")
	private String phone;

	@NotNull(message = "password cannot be empty ")
	@Size(min = 8, max = 16, message = "password must be in between 8 to 16 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$^&+=]).{8,16}$", message = "Passwordd contain atleast on number,one lowecase,one uppercase,one special Character(@#$%^&+=)")
	private String password;

	@PastOrPresent(message = "Created date cannot be in the future")
	private LocalDateTime createdAt;

	@PastOrPresent(message = "Updated date cannot be in the future")
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();

	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	
	public User() {
		
	}

	public User(Long id,
			@NotBlank(message = "name cannot be empty:") @Size(min = 2, max = 20
			, message = "firstName must be in between 2 to 20 characters") String firstName,
			@NotBlank(message = "name cannot be empty:") @Size(min = 2, max = 20
			, message = "LastName must be in between 2 to 20 characters") String lastName,
			@NotNull(message = "Email is Mandatory:") @Email String email,
			@NotBlank(message = "phone is mandatory") @Size(min = 10, max = 15
			, message = "Phone Number must be in between 10 to 15 digits") @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$"
			, message = "Invalid phone number") String phone,
			@NotNull(message = "password cannot be empty ") @Size(min = 8
			, max = 16, message = "password must be in between 8 to 16 characters") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$^&+=]).{8,16}$"
			, message = "Passwordd contain atleast on number,one lowecase,one uppercase,one special Character(@#$%^&+=)") String password,
			@PastOrPresent(message = "Created date cannot be in the future") LocalDateTime createdAt,
			@PastOrPresent(message = "Updated date cannot be in the future") LocalDateTime updatedAt) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
