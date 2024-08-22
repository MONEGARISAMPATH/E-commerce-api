package com.e_commerce.OrderService.OrderService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_commerce.OrderService.OrderEntity.Order;
import com.e_commerce.OrderService.OrderRepository.OrderRepository;


@Service
public class OrderService {
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    public List<Order> getAllOrders() {
    	return orderRepository.findAll();
    	}

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public String getOrderStatusById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        return (order != null) ? order.getStatus() : "Order not found";
        
       /* Order orderStatus = orderRepository.findById(orderId).orElse(null);
		if (orderStatus != null) 
			return orderStatus.getStatus();
		else
			return "oder not found";
    }*/
    }
    
	public Optional<Order> updateOrder(Long orderId, Order updateOrder) {
		Optional<Order> excistOrder = orderRepository.findById(orderId);
		if (excistOrder.isPresent()) {
			Order updatedOrder = excistOrder.get();
			updatedOrder.setStatus(updateOrder.getStatus());
			orderRepository.save(updatedOrder);
			return Optional.of(updatedOrder);
		} else {
			log.warn("Order with ID {} not found", orderId);
			return Optional.empty();
		}

	}
	
   }
    

