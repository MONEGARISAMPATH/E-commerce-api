package com.e_commerce.PaymentService.PaymentDto;

import com.e_commerce.PaymentService.Entity.Payment;

public class PaymentDto {
	private Payment payment;
	private String orderStatus;
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
