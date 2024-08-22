package com.e_commerce.OrderService.OrderEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@NotNull(message = "User Id is mandatory")
	@Positive(message = "userId must be  a positive value")
	private Long userId;

	@NotNull(message = "Product Id is mandatory")
	@Positive(message = "productId must be  a positive value")
	private Long productId;

	@NotNull(message = "Quantity is mandatory")
	@Positive(message = "qunatity must a positive value")
	private Integer quantity;

	@NotBlank(message = "Status is mandatory")
	private String status;

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

	public Order() {

	}

	public Order(Long orderId,
			@NotNull(message = "User Id is mandatory") @Positive(message = "userId must be  a positive value") Long userId,
			@NotNull(message = "Product Id is mandatory") @Positive(message = "productId must be  a positive value") Long productId,
			@NotNull(message = "Quantity is mandatory") @Positive(message = "qunatity must a positive value") Integer quantity,
			@NotBlank(message = "Status is mandatory") String status,
			@PastOrPresent(message = "Created date cannot be in the future") LocalDateTime createdAt,
			@PastOrPresent(message = "Updated date cannot be in the future") LocalDateTime updatedAt) {
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	
}
