package com.example.restaurantappserver.api.controller;

import com.example.restaurantappserver.api.dto.EmployeeDto;
import com.example.restaurantappserver.api.dto.EmployeeJpaDtoWithoutSalary;
import com.example.restaurantappserver.api.service.EmployeeManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ("/api/employee"))
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class EmployeeManagementController {

    private final EmployeeManagementService employeeManagementService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmployeeJpaDtoWithoutSalary> getListOfEmployees(){
        return employeeManagementService.getListOfEmployees();
    }

    @GetMapping(value = "/details")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeDto getEmployeeDetails(@RequestParam("id") Long id){
        return employeeManagementService.getEmployeeById(id);
    }

    @PostMapping(value = "/addnewemployee")
    @PreAuthorize("hasRole('ADMIN')")
    public void addNewEmployee(@RequestBody EmployeeDto employeeDto){
        employeeManagementService.saveNewEmployee(employeeDto);
    }

    @DeleteMapping(value = "/fireemployee")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeEmployee(@RequestParam("id") Long id){
        employeeManagementService.removeEmployeeById(id);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateEmployee(@RequestParam("id") Long id, @RequestBody EmployeeDto employeeDto){
        employeeManagementService.saveNewEmployee(employeeDto);
    }
}
