package com.example.user_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_management.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByName(String name);
}