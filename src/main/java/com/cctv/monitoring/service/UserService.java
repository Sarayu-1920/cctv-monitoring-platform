package com.cctv.monitoring.service;

import com.cctv.monitoring.entity.Role;
import com.cctv.monitoring.entity.User;
import com.cctv.monitoring.repository.RoleRepository;
import com.cctv.monitoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String roleName) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    public Optional<User> authenticate(String username, String password) {

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent() &&
                passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }

        return Optional.empty();
    }
}
