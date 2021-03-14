package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.UserDto;
import com.example.restaurantappserver.entity.User;

public interface UserService {

    void save(UserDto user);

    User findByUserName(String userName);

    UserDto finyById(Long id);
}
