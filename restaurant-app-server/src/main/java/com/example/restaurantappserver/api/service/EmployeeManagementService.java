package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.EmployeeDto;
import com.example.restaurantappserver.api.dto.EmployeeJpaDtoWithoutSalary;

import java.util.List;

public interface EmployeeManagementService {
    void saveNewEmployee(EmployeeDto employeeDto);

    List<EmployeeJpaDtoWithoutSalary> getListOfEmployees();

    EmployeeDto getEmployeeById(Long id);

    void removeEmployeeById(Long id);
}
