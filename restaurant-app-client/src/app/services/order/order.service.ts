import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Menu } from 'src/app/models/menuDto';
import { Order } from 'src/app/models/orderDto';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  order(order : Order): Promise<any>{
    return this.http.post<any>('api/order/new', order).toPromise();
  }

  listOrders(): Promise<Order[]>{
    return this.http.get<Order[]>('api/order/listall').toPromise();
  }

  listMyorders(id : number): Promise<Order[]>{
    return this.http.get<Order[]>('api/order/getmyorder?id=' + id).toPromise()
  }

  closeOrder(id : number): Promise<any>{
    return this.http.post<any>('api/order/closeorder?id='+id, "").toPromise();
  }

  deleteOreder(id : number) : Promise<any>{
    return this.http.delete<any>('api/order/delete?id=' +id).toPromise();
  }
}
