import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/models/userDto';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http : HttpClient) { }

  login(user : User){
    var userName = user.userName;
    var password = user.password;
    return this.http.post<any>('api/user/signin', {userName, password}).toPromise()
  }

  register(user : User){
    return this.http.post<any>('api/user/signup', user).toPromise()
  }

}
