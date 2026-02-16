package com.cctv.monitoring.controller;

import com.cctv.monitoring.entity.User;
import com.cctv.monitoring.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
// controller layer handles api requests and responses
// extracts request body and send it to service layer
//  client interacts with controller layer
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser, HttpSession session) {

        Optional<User> user;
        user = userRepository.findByUsername(loginUser.getUsername());

        if (user.isPresent() && user.get().getPassword().equals(loginUser.getPassword())) {

            session.setAttribute("user", user.get());
            return ResponseEntity.ok("Login Successful");

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged Out");
    }

    @GetMapping("/status")
    public String backendStatus() {
        return "CCTV Monitoring Backend Running";
    }
}
