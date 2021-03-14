import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/orderDto';
import { Table } from 'src/app/models/tableDto';
import { AuthService } from 'src/app/services/auth/auth.service';
import { OrderService } from 'src/app/services/order/order.service';
import { TableService } from 'src/app/services/table/table.service';

@Component({
  selector: 'restaurant-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  constructor(private tableService : TableService, private orderService : OrderService,
        public auth : AuthService) { }

  tables : Table[] | undefined
  tableInfo!: Table;
  orders! :   Order[];
  orderInfo! : Order | undefined;

  isOrder! : boolean | undefined;
  isAdmin! : boolean;

  myTables : Table[] | undefined;

  async ngOnInit(): Promise<void> {
    await this.tableService.getAllTable().then(t => this.tables = t);
    await this.orderService.listOrders().then(o => this.orders = o);
    this.isAdmin = this.auth.isAdmin();

    if(!this.isAdmin){
      this.fillMyTable()
    }
    
  }

  fillMyTable(){
    this.myTables = this.tables?.filter(t => t.customerId == parseInt(localStorage.getItem("id")!) )
    if(this.myTables?.length === 0){
      this.myTables = this.tables?.filter(t => t.isReserved === false);
    }
  }

  reseveTable(table : Table){
    if(!this.isAdmin){
      table.customerId = parseInt(localStorage.getItem("id")!);
    }
    
    this.tableService.reserveOrFreeTable(table)

    table.isReserved = !table.isReserved
    if(!table.isReserved){
      table.customerId = 0;
      table.userName = "";
      if(!this.isAdmin){
        this.fillMyTable()
      }
    }else{
      if(!this.isAdmin){
        this.myTables = []
        this.myTables.push(table)
      }
    }
  }

  payTable(table : Table){
    if(this.orderInfo === undefined){
      alert("The table was just reserved!")
      return;
    }
    this.orderService.closeOrder(this.orderInfo?.id).then(response =>{
        alert("Table was payed!");
        var id = this.orderInfo?.id;
        var index = new Order();
        this.orders.forEach(o =>{if(o.id === id){index = o} })
        this.orders.splice(this.orders.indexOf(index),1);
      }
      )
      .catch(e => {
        alert("Something went Wrong!")
      })
    this.tableService.reserveOrFreeTable(table).then(response =>{
      table.isReserved = false;
      console.log(table);
    })
  }

  toggleWithTable(table : Table){
    this.tableInfo = table;
    this.orderInfo = this.orders.find(o => o.tableId === table.id && o.orderStatus === "UNDER_PROCESS")
    if(this.orderInfo != undefined){
      this.isOrder = true;
    }
    else{
      this.isOrder = false;
    }
  }

  getStyleClass(isReserved : boolean){
    if(isReserved){
      return "Reserved"
    }
    else{
      return "Free"
    }
  }


  getTypeOfTable(table : Table) : string{
    if(table.room === 2){
      return "Small";
    }
    if(table.room === 4){
      return "Normal";
    }
    return "Large";
  }

}
