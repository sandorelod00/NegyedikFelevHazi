package com.example.restaurantappserver.repo;

import com.example.restaurantappserver.api.dto.EmployeeJpaDtoWithoutSalary;
import com.example.restaurantappserver.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long id);

    @Query(value = "select e.id as id, e.firstName as firstName, e.lastName as lastName, e.position as position from Employee as e")
    List<EmployeeJpaDtoWithoutSalary> getListOfEmployees();
}
