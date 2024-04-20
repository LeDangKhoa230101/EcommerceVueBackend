package com.example.vuejsbackend.VueBackend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vuejsbackend.VueBackend.common.ApiResponse;
import com.example.vuejsbackend.VueBackend.dto.cart.CartDto;
import com.example.vuejsbackend.VueBackend.dto.cart.CartItemDto;
import com.example.vuejsbackend.VueBackend.dto.checkout.OrderDto;
import com.example.vuejsbackend.VueBackend.exceptions.CustomException;
import com.example.vuejsbackend.VueBackend.model.Cart;
import com.example.vuejsbackend.VueBackend.model.Product;
import com.example.vuejsbackend.VueBackend.model.User;
import com.example.vuejsbackend.VueBackend.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	ProductService productService;

	@Autowired
	CartRepository reposCart;
	
	public ResponseEntity<ApiResponse> addToCart(Integer productId, int quantity, User user) {
		Product product = productService.findById(productId);
		
		if(product == null) {
			return new ResponseEntity<>(new ApiResponse(true, "Product not found"), HttpStatus.OK);
		}
		
		Optional<Cart> existCart = reposCart.findByProduct(product);
		if(existCart.isPresent()) {
			// Nếu sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng
			Cart cart = existCart.get();
			cart.setQuantity(cart.getQuantity() + quantity);
			cart.setPrice(cart.getQuantity() * cart.getProduct().getPrice());
			reposCart.save(cart);
		} else {
			// Nếu sản phẩm chưa tồn tại trong giỏ hàng, tạo một mục mới
			Cart cart = new Cart();
			cart.setProduct(product);
			cart.setQuantity(quantity);
			cart.setUser(user);
			cart.setPrice(cart.getQuantity() * cart.getProduct().getPrice());
			cart.setCreatedDate(new Date());
			reposCart.save(cart);
		}
		
		return new ResponseEntity<>(new ApiResponse(true, "Added cart successfull!"), HttpStatus.CREATED);
	}

	public CartDto listCartItems(User user) {
		List<Cart> cartList = reposCart.findAllByUserOrderByCreatedDateDesc(user);

		List<CartItemDto> cartItems = new ArrayList<CartItemDto>();

		double totalCost = 0;

		for (Cart cart : cartList) {
			CartItemDto cartItemDto = new CartItemDto(cart);
			totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}

		CartDto cartDto = new CartDto();
		cartDto.setCartItems(cartItems);
		cartDto.setTotalCost(totalCost);

		return cartDto;
	}

	public void deleteCartItem(Long cartItemId, User user) {
		Optional<Cart> optionalCart = reposCart.findById(cartItemId);

		if (optionalCart.isEmpty()) {
			throw new CustomException("Id mặt hàng không hợp lệ: " + cartItemId);
		}

		Cart cart = optionalCart.get();
		if (cart.getUser() != user) {
			throw new CustomException("cart item không thuộc về người dùng: " + cartItemId);
		}

		reposCart.delete(cart);

	}

	public void updateCartItem(Long cartItemId, Integer quantity, User user) {
		Optional<Cart> optionalCart = reposCart.findById(cartItemId);
		
		if (optionalCart.isEmpty()) {
			throw new CustomException("Id mặt hàng không hợp lệ: " + cartItemId);
		}

		Cart cart = optionalCart.get();
		if (cart.getUser() != user) {
			throw new CustomException("cart item không thuộc về người dùng: " + cartItemId);
		}
		
		cart.setQuantity(quantity);
		reposCart.save(cart);
		
	}

	public OrderDto createOrder(User user) {
		List<Cart> cartList = reposCart.findAllByUserOrderByCreatedDateDesc(user);
		
		List<CartItemDto> cartItems = new ArrayList<CartItemDto>();
		
		double totalPrice = 0;
		
		for(Cart cart : cartList) {
			CartItemDto cartItemDto = new CartItemDto(cart);
			totalPrice += cartItemDto.getQuantity() * cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}
		
		OrderDto orderDto = new OrderDto();
		orderDto.setItems(cartItems);
		orderDto.setTotalPrice(totalPrice);
		
		return orderDto;
	}

	public void clearCart(User user) {
		reposCart.deleteAllByUser(user);
	} 

}
