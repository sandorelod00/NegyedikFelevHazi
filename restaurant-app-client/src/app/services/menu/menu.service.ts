import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Menu } from 'src/app/models/menuDto';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) { }

  getMenu(): Promise<Menu[]>{
    return this.http.get<Menu[]>('api/menu/list').toPromise();
  }

  addMenuItem(newMenuItem: Menu) : Promise<any> {
    return this.http.post('api/menu/addmenuitem', newMenuItem).toPromise();
  }

  removeMenuItem(id: number) : Promise<any>{
    return this.http.delete('api/menu/remove?id='+id).toPromise();
  }

  updateMenuItem(menuItem: Menu) : Promise<any> {
    return this.http.put('api/menu/update', menuItem).toPromise();
  }
}
