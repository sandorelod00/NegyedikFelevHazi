package com.example.restaurantappserver.api.service;

import com.example.restaurantappserver.api.dto.EmployeeDto;
import com.example.restaurantappserver.api.dto.EmployeeJpaDtoWithoutSalary;
import com.example.restaurantappserver.repo.EmployeeRepository;
import com.example.restaurantappserver.util.EntityConverterToDto;
import com.example.restaurantappserver.util.DtoConverterToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeManagerServiceImpl implements EmployeeManagementService {

    private final EmployeeRepository employeeRepository;
    private final EntityConverterToDto entityConverterToDto;
    private final DtoConverterToEntity dtoConverterToEntity;

    @Override
    public void saveNewEmployee(EmployeeDto EmployeeDto) {
        employeeRepository.save(dtoConverterToEntity.returnEmployee(EmployeeDto));
    }

    @Override
    public List<EmployeeJpaDtoWithoutSalary> getListOfEmployees() {
        return employeeRepository.getListOfEmployees();
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        return entityConverterToDto.returnEmployeeDto(employeeRepository.findById(id).get());
    }

    @Override
    public void removeEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
