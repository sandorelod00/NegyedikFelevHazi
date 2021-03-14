import {CommonModule} from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/models/employeeDto';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'restaurant-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  constructor(private employeeService: EmployeeService) { }
  
  employees: Array<Employee> | undefined;

  ngOnInit(): void {
  }

  getEmployees(){
    this.employeeService.getAll().then(a => this.employees = a);
    console.log(this.employees);
  }

}
