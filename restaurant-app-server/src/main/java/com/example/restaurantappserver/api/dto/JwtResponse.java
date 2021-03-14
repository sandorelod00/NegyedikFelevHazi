package com.example.restaurantappserver.api.dto;

import com.example.restaurantappserver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;
    private String tokenType;
    private Long id;
    private String username;
    private List<String> roles;
}
