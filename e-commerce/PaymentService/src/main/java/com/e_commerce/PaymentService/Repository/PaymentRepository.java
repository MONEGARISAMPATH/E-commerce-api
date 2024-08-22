package com.e_commerce.PaymentService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.PaymentService.Entity.Payment;
@Repository
public interface PaymentRepository  extends JpaRepository<Payment,Long>{

}
