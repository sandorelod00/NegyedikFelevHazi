import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Table } from 'src/app/models/tableDto';

@Injectable({
  providedIn: 'root'
})
export class TableService {


  constructor(private http : HttpClient) { }

  getAllTable() : Promise<Table[]>{
   this.http.get<Table[]>('api/table/all').toPromise()
   return this.http.get<Table[]>('api/table/all').toPromise();
  }

  getFreeTables() : Promise<Table[]> {
    return this.http.get<Table[]>('api/table/available').toPromise();
  }

  reserveOrFreeTable(table : Table) : Promise<any>{
    return this.http.post<any>('api/table/reserve', table).toPromise();
  }

}
