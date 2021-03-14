import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/models/employeeDto';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http : HttpClient) { }

  getAll() : Promise<Employee[]>{
      return this.http.get<Employee[]>('http://localhost:8080/api/employee/all').toPromise()
  }
}
