package com.hoangtien2k3.repository;

import com.hoangtien2k3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // find by Username return Optional<User>
    Boolean existsByEmail(String email);            // check email is existed in database ?
    Optional<User> findByUsernameOrEmail(String username, String email); // find by email or username
    boolean existsByUsername(String username);      // check username is existed ?
}