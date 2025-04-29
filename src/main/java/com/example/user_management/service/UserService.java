package com.example.user_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.exception.ResourceNotFoundException;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("User not found with id:" +id));
    }

    public User addUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        User existingUser=userRepository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("User not found with id:" +id));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        // existingUser.setPassword(user.getPassword());
        existingUser.setPassword(encodedPassword);

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found with id:" +id);
        }
        userRepository.deleteById(id);
    }

    public User getUserByName(String name){
        return userRepository.findByName(name)
            .orElseThrow(()->new ResourceNotFoundException("User not found with name:" +name));
    }
    // Add authentication method
    // public User authenticate(String email, String password) {
    //     User user = userRepository.findByEmail(email);  // Assuming you have a method like this in your UserRepository
    //         .orElseThrow(() -> new ResourceNotFoundException("User not found with email:" + email));
    //     if (user != null && passwordEncoder.matches(password, user.getPassword())) {
    //         return user;  // Return user if credentials are valid
    //     }
    //     return null;  // Invalid credentials
    // }
    // Add authentication method
public User authenticate(String email, String password) {
    // Unwrap the Optional<User> returned by findByEmail and throw an exception if not found
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with email:" + email));

    // Check if the provided password matches the encoded password
    if (passwordEncoder.matches(password, user.getPassword())) {
        return user;  // Return user if credentials are valid
    }

    return null;  // Return null if the password doesn't match
}

}
