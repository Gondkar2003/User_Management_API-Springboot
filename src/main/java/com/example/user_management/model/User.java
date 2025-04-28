package com.example.user_management.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="user1")
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long user_id;

    @Column(name="user_name")
    private String name;

    @Column(name="email", nullable=false, unique=true)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    @Schema(description="Email address of user", example="jonhdoe@test.com")
    private String email;

    @Column(name="password", nullable=false)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "Password must contain at least 8 characters, including uppercase, lowercase and a number")
    @Schema(description = "Pasword of the user", example = "Abc@1234")
    private String password;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
} 
