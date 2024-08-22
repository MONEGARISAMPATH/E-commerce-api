package com.e_commerce.CartService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Cart Description Cannot Be Empty")
	@Size(min = 10, max = 60, message = "Cart description must in Between 10 to 60 characters")
	private String description;
	@NotNull(message = "Toatl cannot be null")
	@PositiveOrZero(message = "Total must be zero or a positive value")
	private Double total;

	
	public Cart() {
	
	}

	public Cart(Long id,
			@NotBlank(message = "Cart Description Cannot Be Empty") @Size(min = 10, max = 60, message = "Cart description must in Between 10 to 60 characters") String description,
			@NotNull(message = "Toatl cannot be null") @PositiveOrZero(message = "Total must be zero or a positive value") Double total) {
		this.id = id;
		this.description = description;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
