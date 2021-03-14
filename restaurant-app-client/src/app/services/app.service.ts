import { HttpClient, HttpHeaders, HttpParameterCodec, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/userDto';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  authenticated = false;

  constructor(private http: HttpClient) {}

  register(user : User) : Promise<String>{
    return this.http.post<String>('api/user/registration', user).toPromise();
  }

  login(user : User) : Promise<any>{
    let options = {"body" : user}
    return this.http.post<any>('api/user/',user).toPromise();

  }

}
