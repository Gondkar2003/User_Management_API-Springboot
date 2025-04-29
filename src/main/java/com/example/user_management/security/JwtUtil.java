// package com.example.user_management.security;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import java.util.Date;

// import org.springframework.stereotype.Component;

// @Component
// public class JwtUtil {

//     private String secretKey = "yourSecretKey"; // You can use an environment variable or more secure method for storing this

//     // Generate a JWT token
//     public String generateToken(String username) {
//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Token expires in 1 hour
//                 .signWith(SignatureAlgorithm.HS256, secretKey)
//                 .compact();
//     }

//     // Validate the token
//     public boolean validateToken(String token, String username) {
//         String tokenUsername = Jwts.parser()
//                 .setSigningKey(secretKey)
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//         return tokenUsername.equals(username) && !isTokenExpired(token);
//     }

//     // Check if the token is expired
//     private boolean isTokenExpired(String token) {
//         Date expirationDate = Jwts.parser()
//                 .setSigningKey(secretKey)
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getExpiration();
//         return expirationDate.before(new Date());
//     }
// }
package com.example.user_management.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private String secretKey = "yourSecretKey"; // Secret key

    // Generate a JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, secretKey)  // <-- correct for 0.9.1
                .compact();
    }

    // Validate the token
    public boolean validateToken(String token, String username) {
        String tokenUsername = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        Date expirationDate = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirationDate.before(new Date());
    }
}
