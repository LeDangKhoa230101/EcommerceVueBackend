package com.example.vuejsbackend.VueBackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vuejsbackend.VueBackend.model.Category;
import com.example.vuejsbackend.VueBackend.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}
	 
}
