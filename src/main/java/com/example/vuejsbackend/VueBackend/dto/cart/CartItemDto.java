package com.example.vuejsbackend.VueBackend.dto.cart;

import com.example.vuejsbackend.VueBackend.model.Cart;
import com.example.vuejsbackend.VueBackend.model.Product;

public class CartItemDto {
	private Long id;
	private Integer quantity;
	private double price;
	private Product product;

	public CartItemDto() {
		super();
	}
	
	public CartItemDto(Cart cart) {
		this.id = cart.getId();
		this.quantity = cart.getQuantity();
		this.price = cart.getPrice();
		this.setProduct(cart.getProduct());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
