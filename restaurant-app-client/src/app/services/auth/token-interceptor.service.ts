import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  intercept(req : HttpRequest<any>, next : HttpHandler){
    if(localStorage.getItem('Authorization') != null){
      let tokenizedReq = req.clone({
        headers: req.headers.set("Authorization", "Bearer "+ localStorage.getItem('Authorization')).set('X-Requested-With', 'XMLHttpRequest')
      })
      console.log(tokenizedReq);
      return next.handle(tokenizedReq)
    }
    else{
      let tokenizedReq = req.clone({
          headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
      })
      return next.handle(tokenizedReq)
    }
  }

}

