import { HttpClient } from '@angular/common/http';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Item } from 'src/app/models/itemDto';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  urlPath = 'http://localhost:8080/api'

  constructor(private http : HttpClient) { }
  
  getAllItemInStorage(): Promise<Item[]>{
    return this.http.get<Item[]>('api/storage/listallitem').toPromise();
  }

  getDrinks(): Promise<Item[]>{
    return this.http.get<Item[]>('api/storage/getdrinks').toPromise();
  }

  getItemById(id : number): Promise<Item>{
    return this.http.get<Item>('api/storage/getitem?id='+id).toPromise();
  }

  saveItem(item : Item): Promise<any>{
    return this.http.put( 'api/storage/updateitem', item).toPromise();
  }

  saveNewItem(newItem: Item) : Promise<any> {
    return this.http.post('api/storage/additem', newItem).toPromise();
  }

  deleteItem(id : number) : Promise<any>{
    return this.http.delete('api/storage/deletitem?id='+id).toPromise();
  }

}
