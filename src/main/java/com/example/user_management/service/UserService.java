package com.example.user_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.exception.ResourceNotFoundException;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("User not found with id:" +id));
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        User existingUser=userRepository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("User not found with id:" +id));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found with id:" +id);
        }
        userRepository.deleteById(id);
    }

}
