package com.example.vuejsbackend.VueBackend.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vuejsbackend.VueBackend.common.ApiResponse;
import com.example.vuejsbackend.VueBackend.dto.ProductDto;
import com.example.vuejsbackend.VueBackend.exceptions.ProductNotExistsException;
import com.example.vuejsbackend.VueBackend.model.Category;
import com.example.vuejsbackend.VueBackend.model.Product;
import com.example.vuejsbackend.VueBackend.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired 
	private ProductRepository reposProduct;
	
	@Autowired
	private CategoryService categoryService;
	
	public List<Product> getAllProduct() {
		List<Product> allProducts = reposProduct.findAll();
		return allProducts;
	}
	
	public List<Product> searchProduct(String name) {
		return reposProduct.findProductsByCategoryName(name);
	}

	public Product findById(Integer productId) {
		Optional<Product> optionalProduct =  reposProduct.findById(productId);
	
		if(optionalProduct.isEmpty()) {
			throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
	}

	public ResponseEntity<ApiResponse> addProduct(ProductDto productDto) {
		Optional<Category> cate = categoryService.findById(productDto.getCategory());
		
		Product product = new Product();
		product.setImage(productDto.getImage());
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setCategory(cate.get());
		
		reposProduct.save(product); 
		 
		return new ResponseEntity<>(new ApiResponse(true, "Add product success!"), HttpStatus.CREATED);
	}

	public ResponseEntity<ApiResponse> updateProduct(Integer productId, ProductDto productDto) {
		Optional<Category> cate = categoryService.findById(productDto.getCategory());
		
		Optional<Product> productOptional = reposProduct.findById(productId);
		
		if(productOptional.isEmpty()) {
			throw new ProductNotExistsException("product id is invalid: " + productId);
        }
		
		Product product = productOptional.get();
		product.setImage(productDto.getImage());
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setCategory(cate.get());
		
		reposProduct.save(product);
		
		return new ResponseEntity<>(new ApiResponse(true, "Update product success!"), HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> deleteProduct(Integer productId) {
		reposProduct.deleteById(productId);
		
		return new ResponseEntity<>(new ApiResponse(true, "Delete product success!"), HttpStatus.OK);
	}

}
