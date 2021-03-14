package com.example.restaurantappserver.api.dto;

import com.example.restaurantappserver.enums.Position;

public interface EmployeeJpaDtoWithoutSalary {
    Long getId();
    String getFirstName();
    String getLastName();
    Position getPosition();
}
