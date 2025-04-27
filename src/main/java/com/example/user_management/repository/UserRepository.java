package com.example.user_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_management.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public User findByName(String name);
    
}