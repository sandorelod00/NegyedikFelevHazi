package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.entity.Role;
import com.example.restaurantappserver.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
