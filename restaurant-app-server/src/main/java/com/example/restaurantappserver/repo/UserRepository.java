package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

        User findByUserName(String username);

        Boolean existsByUserName(String username);
}
