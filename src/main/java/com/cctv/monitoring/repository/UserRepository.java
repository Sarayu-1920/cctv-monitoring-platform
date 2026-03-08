package com.cctv.monitoring.repository;

import com.cctv.monitoring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// model layer => manages crud operations
// talks to database using jpa(java persistence api)
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
