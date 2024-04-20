package com.example.vuejsbackend.VueBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vuejsbackend.VueBackend.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
