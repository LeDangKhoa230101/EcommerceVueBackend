package com.example.vuejsbackend.VueBackend.dto.checkout;

import java.util.List;

import com.example.vuejsbackend.VueBackend.dto.cart.CartItemDto;

public class OrderDto {
	private List<CartItemDto> items;
	private double totalPrice;
	private String phone;
    private String address;
    private String note;

	public OrderDto() {
		super();
	}

	public OrderDto(List<CartItemDto> items, double totalPrice, String phone, String address, String note) {
		super();
		this.items = items;
		this.totalPrice = totalPrice;
		this.phone = phone;
		this.address = address;
		this.note = note;
	}

	public List<CartItemDto> getItems() {
		return items;
	}

	public void setItems(List<CartItemDto> items) {
		this.items = items;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
