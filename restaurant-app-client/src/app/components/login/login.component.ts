import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/userDto';
import { AppService } from 'src/app/services/app.service';
import { LoginService } from 'src/app/services/auth/login.service';

@Component({
  selector: 'restaurant-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private app : AppService, private http : HttpClient, private router : Router, private loginService : LoginService ) { }

  isLogin = true;

  userName! : String;
  password! : String;
  confirmPassword! :String;
  ngOnInit(): void {
  }

  signUp(){
    if(this.password != this.confirmPassword){
      alert("Passwords Mismatch")
      return;
    }
    var user = new User()
    user.userName = this.userName
    user.password = this.password
    this.loginService.register(user).then(data => {
      if(!data){
        alert("Registration Faild")
        return;
      }else{
        alert("Success")
        this.login()
      }
    })
  }

  login(){
    if(this.userName === undefined){
      alert("User name is empty")
      return;
    }
    if(this.password === undefined){
      alert("Password is empty")
      return;
    }
    var user = new User()
    user.userName = this.userName;
    user.password = this.password;
    //this.app.login(user).then(r => console.log(r));
      this.loginService.login(user).then(data => {
          localStorage.setItem(
            "Authorization",  data.accessToken
          );
          localStorage.setItem(
            "id" , data.id
          )
          localStorage.setItem(
            "role", data.roles[0]
          )
          this.router.navigate(['/order'])
      }).catch(error => {
        alert("Invalid User!")
        return;  
      })


  }

}
