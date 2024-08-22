package com.e_commerce.PaymentService.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.PaymentService.Entity.Payment;
import com.e_commerce.PaymentService.ErrorResponse.ErrorResponse;
import com.e_commerce.PaymentService.GlobalExceptionHandler.PaymentNotFoundException;
import com.e_commerce.PaymentService.PaymentDto.PaymentDto;
import com.e_commerce.PaymentService.Service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	@Autowired
	PaymentService paymentService;
	@Autowired
	Environment environment;

	@GetMapping("/port")
	public String portNumberStatus() {
		return "PaymentService running port number is :" + " " + environment.getProperty("local.server.port");

	}

	@GetMapping("/")
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> allPaymentDetails = paymentService.getAllPaymentDetials();
		return new ResponseEntity<>(allPaymentDetails, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
		try {
			Payment payment = paymentService.getPaymentById(id);
			return ResponseEntity.ok(payment);
		} catch (PaymentNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@GetMapping("/orderStatus/{id}/{orderId}")
	public ResponseEntity<?> getPaymentById(@PathVariable Long id, @PathVariable Long orderId) {
		Optional<PaymentDto> paymentdto = paymentService.getPaymentById(id, orderId);

		if (paymentdto.isPresent()) {
			PaymentDto payment = paymentdto.get();
			return new ResponseEntity<>(payment, HttpStatus.OK);
		} else {
			String errorMessage = "Payment not found with ID: " + id;

			logger.error("Error: {} | Status: {}", errorMessage, HttpStatus.NOT_FOUND);
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/")
	public ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment) {
		Payment payments = paymentService.createPayment(payment);
		return new ResponseEntity<>(payments, HttpStatus.OK);

	}

}
