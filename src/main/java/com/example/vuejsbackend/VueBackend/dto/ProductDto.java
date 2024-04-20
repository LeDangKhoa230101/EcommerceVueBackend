package com.example.vuejsbackend.VueBackend.dto;

public class ProductDto {
	private String name;
	private String description;
	private String image;
	private double price;
	private Integer category;

	public ProductDto() {
		super();
	}

	public ProductDto(String name, String description, String image, double price, Integer category) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}


}
