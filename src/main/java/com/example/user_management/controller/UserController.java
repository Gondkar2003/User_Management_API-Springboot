package com.example.user_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.example.user_management.model.User;
import com.example.user_management.service.UserService;
// import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController{

    @Autowired
    private UserService userService;
    
    @Operation(summary="Get List of All Users", description="Retreives all user details")
    @ApiResponses(value= {
        @ApiResponse(responseCode="201", description="Users Retreived successfully"),
        @ApiResponse(responseCode="500", description="Internal server error"),
    })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
    List<User> user=userService.getAllUsers();
    return ResponseEntity.ok(user);
    }

    @Operation(summary = "Add a new User", description = "Adds a new User.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid User data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        User newUser=userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Operation(summary="Get User by ID", description="Retreives a User by ID")
    @ApiResponses(value={
        @ApiResponse(responseCode = "201", description = "User Retreived Successfully"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping("/id/{id}") 
        public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
            User user=userService.getUserById(id);
            return ResponseEntity.ok(user);
    }

    @Operation(summary="Update User by id", description = "Updates USer by id")
    @ApiResponses(value={
        @ApiResponse(responseCode = "201", description="User updated successfully"),
        @ApiResponse(responseCode = "500", description="Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user ){
        User newUser=userService.updateUser(id, user);
        return ResponseEntity.ok(newUser);
    }

    @Operation(summary ="Delete User by ID", description = "Deletes user deatils using ID")
    @ApiResponses(value={
        @ApiResponse(responseCode = "201", description = "User Deleted successfully"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get User by name", description = "Retreives User by name")
    @ApiResponses(value={
        @ApiResponse(responseCode = "201", description = "User Retreived by name successfully"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable("name") String name){
        User user=userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }
}


