package com.example.vuejsbackend.VueBackend.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vuejsbackend.VueBackend.dto.cart.CartItemDto;
import com.example.vuejsbackend.VueBackend.dto.checkout.OrderDto;
import com.example.vuejsbackend.VueBackend.model.Order;
import com.example.vuejsbackend.VueBackend.model.OrderItem;
import com.example.vuejsbackend.VueBackend.model.User;
import com.example.vuejsbackend.VueBackend.repository.OrderItemRepository;
import com.example.vuejsbackend.VueBackend.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Transactional
	public OrderDto checkout(String phone, String address, String note, User user) {
		// Lấy thông tin đơn hàng từ giỏ hàng
        OrderDto orderDto = cartService.createOrder(user);
		
        // Tạo đơn hàng mới từ thông tin trong orderDto và các thông tin mới
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setPhone(phone);
        order.setAddress(address);
        order.setNote(note);
        order.setCreatedDate(new Date());
        
        orderRepository.save(order);
        
        // Lưu các mục đơn hàng vào cơ sở dữ liệu
        for(CartItemDto cartItem : orderDto.getItems()) {
        	OrderItem orderItem = new OrderItem();
        	orderItem.setOrder(order);
        	orderItem.setProduct(cartItem.getProduct());
        	orderItem.setQuanty(cartItem.getQuantity());
        	orderItem.setPrice(cartItem.getProduct().getPrice());
        	orderItem.setCreatedDate(new Date());
        	orderItemRepository.save(orderItem);
        }
        
        cartService.clearCart(user);
        
		return orderDto;
	}

}
