package com.e_commerce.PaymentService.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Card Number is mandaatory")
	@Pattern(regexp = "\\d{16}", message = "Card number must be equal to 16 digits")
	private String cardNumber;

	@NotBlank(message = "CVV is mandatory")
	@Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
	private String cvv;

	@NotNull(message = "Amount is mandatory")
	@DecimalMin(value = "0.01", inclusive = false, message = "Amount must be greater than Zero")
	@Digits(integer = 10, fraction = 2, message = "Amount must have upt to 10 digits in Integer format and "
			+ "two Digits fractional value  allowed")
	private BigDecimal amount;

	@NotBlank(message = "PaymentMethod is mandatory")
	@Size(max = 20, message = "Payment must be less than 20 characters")
	@Pattern(regexp = "^(CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER|CASH_ON_DELIVERY)$", message = "Payment method must be one of: CREDIT_CARD, DEBIT_CARD, PAYPAL, BANK_TRANSFER, CASH_ON_DELIVERY")
	private String paymentMethod;

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

	

	public Payment() {

	}

	public Payment(Long id,
			@NotBlank(message = "Card Number is mandaatory") @Pattern(regexp = "\\d{16}", message = "Card number must be equal to 16 digits") String cardNumber,
			@NotBlank(message = "CVV is mandatory") @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits") String cvv,
			@NotNull(message = "Amount is mandatory") @DecimalMin(value = "0.01", inclusive = false, message = "Amount must be greater than Zero") @Digits(integer = 10, fraction = 2, message = "Amount must have upt to 10 digits in Integer format and two Digits fractional value  allowed") BigDecimal amount,
			@NotBlank(message = "PaymentMethod is mandatory") @Size(max = 20, message = "Payment must be less than 20 characters") @Pattern(regexp = "^(CREDIT_CARD|DEBIT_CARD|PAYPAL|BANK_TRANSFER|CASH_ON_DELIVERY)$", message = "Payment method must be one of: CREDIT_CARD, DEBIT_CARD, PAYPAL, BANK_TRANSFER, CASH_ON_DELIVERY") String paymentMethod,
			@PastOrPresent(message = "Created date cannot be in the future") LocalDateTime createdAt,
			@PastOrPresent(message = "Updatedd date cannot be in the future") LocalDateTime updatedAt) {
		
		this.id = id;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
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
