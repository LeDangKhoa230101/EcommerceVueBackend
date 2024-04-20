package com.example.vuejsbackend.VueBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vuejsbackend.VueBackend.model.AuthenticationToken;
import com.example.vuejsbackend.VueBackend.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long>{
	
	AuthenticationToken findByUser(User user);
    
	AuthenticationToken findByToken(String token);
}
