package com.e_commerce.OrderService.OrderRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_commerce.OrderService.OrderEntity.Order;

@Repository

public interface OrderRepository extends JpaRepository<Order, Long> {

}
