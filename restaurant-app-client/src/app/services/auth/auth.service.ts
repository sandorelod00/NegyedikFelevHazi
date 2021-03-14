import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private _router : Router) { }

  loggedIn(){
    return localStorage.getItem("Authorization");
  }

  isAdmin(){
  
  return localStorage.getItem("role") === "ROLE_ADMIN" ? true : false
  }



  logoutUser(){
    localStorage.removeItem('Authorization')
    localStorage.removeItem('id')
    localStorage.removeItem('role')
    this._router.navigate(["login"])
  }
}
