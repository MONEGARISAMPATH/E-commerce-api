package com.e_commerce.CartService.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.e_commerce.CartService.Entity.Cart;
import com.e_commerce.CartService.Repository.CartRepository;
import com.e_commerce.CartService.Responses.ResponseCartDelete;



@Service
public class CartService {
	//private static final Logger log = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	CartRepository cartRespository;

	public Cart createCart(Cart cart) {
		return cartRespository.save(cart);
	}

	public List<Cart> getAllCartList() {
		return cartRespository.findAll();

	}

	public Optional<Cart> getcartById(Long id) {
		return cartRespository.findById(id);
	}

	public Optional<Cart> updateCart(Long id, Cart cart) {
		Optional<Cart> cartId = cartRespository.findById(id);
		if (cartId.isPresent()) {
			Cart excistCart = cartId.get();
			excistCart.setDescription(cart.getDescription());
			excistCart.setTotal(cart.getTotal());
			Cart updatedCart = cartRespository.save(excistCart);
			return Optional.of(updatedCart);
		} else {
			// return cartId.orElse(null);
			// return Optional.empty();
			throw new RuntimeException("Cart not found" + id);
		}
	}

	public ResponseCartDelete deleteCart(Long id) {
		cartRespository.deleteById(id);
		ResponseCartDelete delete = new ResponseCartDelete();
		delete.setId(id);
		delete.setDeleteMsg("cart Deleted !");
		return delete;
	}

}
