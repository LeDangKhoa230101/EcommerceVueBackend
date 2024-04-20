package com.example.vuejsbackend.VueBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vuejsbackend.VueBackend.common.ApiResponse;
import com.example.vuejsbackend.VueBackend.dto.ProductDto;
import com.example.vuejsbackend.VueBackend.model.Product;
import com.example.vuejsbackend.VueBackend.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService serviceProduct;
	
	@GetMapping("/")
    public List<Product> getProducts() {
        List<Product> productList = serviceProduct.getAllProduct();
        return productList;
    }
	
	@GetMapping("/search")
    public List<Product> getProductsByCategory(@RequestParam String name) {
        return serviceProduct.searchProduct(name);
    }
	
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable Integer productId) {
		return serviceProduct.findById(productId);
	}
	
	@PostMapping("/admin/add-product")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
		return serviceProduct.addProduct(productDto);
	}
	
	@PostMapping("/update-product/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(
			@PathVariable Integer productId, @RequestBody ProductDto productDto) {
		
		return serviceProduct.updateProduct(productId, productDto);
		
	}
	
	@PostMapping("/admin/delete-product/{productId}")
	public ResponseEntity<ApiResponse> addProduct(@PathVariable("productId") Integer productId) {
		return serviceProduct.deleteProduct(productId);
	}
	
}
