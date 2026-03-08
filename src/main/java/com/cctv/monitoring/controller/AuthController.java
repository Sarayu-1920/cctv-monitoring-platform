package com.cctv.monitoring.controller;

import com.cctv.monitoring.entity.User;
import com.cctv.monitoring.service.UserService;
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
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userRequest) {

        try {

            User user = userService.registerUser(
                    userRequest.getUsername(),
                    userRequest.getPassword(),
                    userRequest.getRole().getName()
            );

            return ResponseEntity.ok(user);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser, HttpSession session) {

        Optional<User> user = userService.authenticate(
                loginUser.getUsername(),
                loginUser.getPassword()
        );

        if (user.isPresent()) {
            session.setAttribute("user", user.get());
            session.setAttribute("role", user.get().getRole().getName());
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
