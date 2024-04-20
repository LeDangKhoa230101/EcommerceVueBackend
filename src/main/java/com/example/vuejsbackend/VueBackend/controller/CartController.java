package com.example.vuejsbackend.VueBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vuejsbackend.VueBackend.common.ApiResponse;
import com.example.vuejsbackend.VueBackend.dto.cart.CartDto;
import com.example.vuejsbackend.VueBackend.dto.checkout.OrderDto;
import com.example.vuejsbackend.VueBackend.model.User;
import com.example.vuejsbackend.VueBackend.service.AuthenticationService;
import com.example.vuejsbackend.VueBackend.service.CartService;
import com.example.vuejsbackend.VueBackend.service.OrderService;
import com.example.vuejsbackend.VueBackend.service.ProductService;

@RestController
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/add/{productId}")
    public ResponseEntity<ApiResponse> addToCart(@PathVariable("productId") Integer productId, 
    		@RequestParam int quantity, @RequestParam("token") String token) {
		
		authenticationService.authenticate(token);
		User user = authenticationService.getUser(token);
		 
		return cartService.addToCart(productId, quantity, user);
    }
	
	@GetMapping("/")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
		authenticationService.authenticate(token);
		User user = authenticationService.getUser(token);
		
		CartDto cartDto = cartService.listCartItems(user);
		
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(
			@PathVariable("cartItemId") Long itemId,
			@RequestParam("token") String token) {
		
		authenticationService.authenticate(token);
		User user = authenticationService.getUser(token);
		
		cartService.deleteCartItem(itemId, user);
		
		return new ResponseEntity<>(new ApiResponse(true, "Item has been remove"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<ApiResponse> updateCartItem(
			@PathVariable("cartItemId") Long cartItemId,
			@RequestParam Integer quantity,
			@RequestParam("token") String token) {
		
		authenticationService.authenticate(token);
		User user = authenticationService.getUser(token);
		
		cartService.updateCartItem(cartItemId, quantity, user);
		
		return new ResponseEntity<>(new ApiResponse(true, "Updated successful!"), HttpStatus.OK);
	}
	
	
	@PostMapping("/checkout")
	public OrderDto checkout(
			@RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("note") String note,
			@RequestParam("token") String token) 
	{
		authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
		 
		return orderService.checkout(phone, address, note, user);
	}
	
}
