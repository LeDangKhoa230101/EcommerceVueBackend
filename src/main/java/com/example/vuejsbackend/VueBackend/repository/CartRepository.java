package com.example.vuejsbackend.VueBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vuejsbackend.VueBackend.model.Cart;
import com.example.vuejsbackend.VueBackend.model.Product;
import com.example.vuejsbackend.VueBackend.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

	Optional<Cart> findByProduct(Product product);

	void deleteAllByUser(User user);
}
