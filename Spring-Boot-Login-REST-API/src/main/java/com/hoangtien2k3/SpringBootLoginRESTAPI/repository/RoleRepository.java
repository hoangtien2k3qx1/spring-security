package com.hoangtien2k3.SpringBootLoginRESTAPI.repository;

import com.hoangtien2k3.SpringBootLoginRESTAPI.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}