package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.UserDto;
import com.example.restaurantappserver.entity.User;
import com.example.restaurantappserver.repo.RoleRepository;
import com.example.restaurantappserver.repo.UserRepository;
import com.example.restaurantappserver.util.EntityConverterToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    private final EntityConverterToDto entityConverterToDto;

    @Override
    public void save(UserDto user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(newUser);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDto finyById(Long id) {
        User userDto = userRepository.findById(id).get();
        return entityConverterToDto.returnUserDto(userDto);
    }
}
