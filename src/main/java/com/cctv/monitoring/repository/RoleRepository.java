package com.cctv.monitoring.repository;


import com.cctv.monitoring.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role entity.
 * Provides database access methods for the roles table.
 * Extends JpaRepository to inherit standard CRUD operations.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
