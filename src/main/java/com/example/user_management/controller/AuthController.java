// package com.example.user_management.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import com.example.user_management.model.User;
// import com.example.user_management.repository.UserRepository;
// import com.example.user_management.exception.ResourceNotFoundException;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @PostMapping("/login")
//     public String login(@RequestParam String email, @RequestParam String password) {
//         User user = userRepository.findByEmail(email)
//             .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

//         if (passwordEncoder.matches(password, user.getPassword())) {
//             return "Login successful!";
//         } else {
//             throw new ResourceNotFoundException("Invalid email or password");
//         }
//     }
// }

package com.example.user_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;
import com.example.user_management.security.JwtUtil;
import com.example.user_management.dto.LoginRequest;
import com.example.user_management.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        // Find the user by email
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

        // Check if password matches
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // Generate JWT token and return it
            return jwtUtil.generateToken(user.getEmail());
        } else {
            throw new ResourceNotFoundException("Invalid email or password");
        }
    }
}
