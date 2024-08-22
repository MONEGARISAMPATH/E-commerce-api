package com.e_commerce.CategoryService.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public class ProductDto {
	
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;

	@Min(value = 0, message = "Price must be a positive value")
	@Digits(integer = 10, fraction = 2, message = "Price can have up to 10 digits before the decimal point "
			+ "and after deciaml point upto 2 fractional digits  allowed")
	private BigDecimal price;

	@NotBlank(message = "Slug cannot be blank")
	private String slug;

	@NotNull(message = "Stock cannot be null")
	@Min(value = 0, message = "Stock must be a non-negative number")
	private Long stock;

	@NotBlank(message = "Status cannot be blank")
	@Pattern(regexp = "^(AVAILABLE|OUT_OF_STOCK|DISCONTINUED)$", message = "Status must be one of the predefined values")
	private String status;

	@PastOrPresent(message = "Created date cannot be in the future")
	private LocalDateTime createdAt;

	@PastOrPresent(message = "Updated date cannot be in the future")
	private LocalDateTime updatedAt;

	@NotBlank(message = "Image URL cannot be blank. Please provide a valid URL.")
	@URL(message = "Image URL must be a valid URL in the format: https://example.com/images/product.jpg")
	private String imgUrl;
	
	@PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        slug = name.replaceAll(" ", "-");
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

	public ProductDto() {
		
	}

	public ProductDto(Long id,@NotBlank(message = "Name is mandatory") String name,
			@Min(value = 0, message = "Price must be a positive value") @Digits(integer = 10, fraction = 2, 
			message = "Price can have up to 10 digits before the decimal point and after deciaml point upto 2 fractional digits  allowed") BigDecimal price,
			@NotBlank(message = "Slug cannot be blank") String slug,
			@NotNull(message = "Stock cannot be null") @Min(value = 0, message = "Stock must be a non-negative number") Long stock,
			@NotBlank(message = "Status cannot be blank") @Pattern(regexp = "^(AVAILABLE|OUT_OF_STOCK|DISCONTINUED)$", message = "Status must be one of the predefined values") String status,
			@PastOrPresent(message = "Created date cannot be in the future") LocalDateTime createdAt,
			@PastOrPresent(message = "Updated date cannot be in the future") LocalDateTime updatedAt,
			@NotBlank(message = "Image URL cannot be blank. Please provide a valid URL.") @URL(message = "Image URL must be a valid URL in the format: https://example.com/images/product.jpg") String imgUrl) {
	
		this.id=id;
		this.name = name;
		this.price = price;
		this.slug = slug;
		this.stock = stock;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.imgUrl = imgUrl;
	}

	

	public void setId(Long id) {
		this.id=id;
		
	}
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	

}
