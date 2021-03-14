import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Menu } from 'src/app/models/menuDto';
import { Order } from 'src/app/models/orderDto';
import { Table } from 'src/app/models/tableDto';
import { AuthService } from 'src/app/services/auth/auth.service';
import { OrderService } from 'src/app/services/order/order.service';
import { TableService } from 'src/app/services/table/table.service';

@Component({
  selector: 'restaurant-order-modal',
  templateUrl: './order-modal.component.html',
  styleUrls: ['./order-modal.component.css']
})
export class OrderModalComponent implements OnInit {

  @Input() public cart! : Menu[];
  @Input() public cost! :number;

  constructor(private orderService : OrderService, 
    private tableService : TableService, 
    public activeModal : NgbActiveModal,
    public authService : AuthService) { }

  freeTables! : Table[];
  selectedTableId! : number;
  oldTableId! : number;
  orderType! : string;
  userId! : number;
  isAdmin! : boolean;

  reservedTable : Table | undefined;

  ngOnInit(): void {
    this.tableService.getFreeTables().then(t => this.freeTables = t);
    this.isAdmin = this.authService.isAdmin()!
    if(!this.isAdmin){
      this.userId = parseInt(localStorage.getItem("id")!);
      this.tableService.getAllTable().then(tables => {
        this.reservedTable = tables.find(table => table.customerId === this.userId);
        if(this.reservedTable){
          console.log("Table was reserved :" + this.reservedTable.id)
          this.orderType = "OFFLINE"
          this.selectedTableId = this.reservedTable.id
        }
      })
    }
  }

  order(){
    let order = new Order;
    if(this.cart.length === 0){
      alert("Your cart is empty!")
      return;
    }
    if(this.orderType === undefined){
      alert("Sorry")
      return;
    }
    order.menuItems = this.cart.map(m => m.id);
    order.menuItemsDto = this.cart;

    if(this.orderType == "OFFLINE"){
      order.orderType = "OFFLINE";
      if (this.selectedTableId == 0 ){
        order.tableId = this.oldTableId
      } 
      else{
        order.tableId = this.selectedTableId;
        let  table = this.freeTables.find(t => t.id === this.selectedTableId); 
        this.freeTables.splice(this.freeTables.indexOf(table!), 1);
      }
      if(this.userId != undefined || null){
        order.customerId = this.userId;
      }
    }
    if(this.orderType == "ONLINE"){
      order.orderType = "ONLINE";
      order.customerId = this.userId;
    }

    this.orderService.order(order).then(r => console.log(r));
    this.cost = 0.0;
    this.cart = [];

    this.activeModal.close("Order Saved");
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

  removeFromCart(item : Menu){
    this.cart.splice(this.cart.indexOf(item), 1);
    this.cost -= item.price;
  }
}
