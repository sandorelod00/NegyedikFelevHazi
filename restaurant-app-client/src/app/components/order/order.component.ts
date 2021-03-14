import { Component, OnInit } from '@angular/core';
import { Menu } from 'src/app/models/menuDto';
import { MenuService } from 'src/app/services/menu/menu.service';
import { Order } from 'src/app/models/orderDto';
import { OrderService } from 'src/app/services/order/order.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrderModalComponent } from './order-modal/order-modal.component';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'restaurant-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  isRender!: boolean;
  active = 1;

  constructor(private menuService : MenuService,
     private orderService :OrderService,
     private authService : AuthService,
     private modalService : NgbModal) { }

  menuItems : Menu[] | undefined;
  foods : Menu[] | undefined;
  alcoholicDrinks : Menu[] |undefined;
  nonAlcoholicDrinks : Menu[] | undefined;

  orders: Order[] | undefined;
  isAdmin!: boolean;
  userId!: number;
  myOrders: Order[] |undefined;
  enableOrder : Boolean = true;

  cart! : Menu[];
  cost = 0.0;
  async ngOnInit(): Promise<void> {
    this.isRender = true;
    await this.menuService.getMenu().then(m => this.menuItems = m);
    this.cart = []
    this.foods = this.menuItems?.filter(m => m.recipeId != null);
    this.alcoholicDrinks = this.menuItems?.filter(m => m.itemId != null && m.itemDto.itemType === "ALCOHOLIC_DRINK" );
    this.nonAlcoholicDrinks = this.menuItems?.filter(m => m.itemId != null && m.itemDto.itemType == "NON_ALCOHOLIC_DRINK");
    this.isRender = false;

    
    this.isAdmin = this.authService.isAdmin()!
    this.userId = parseInt(localStorage.getItem("id")!);
    this.refreshLists();
  }

  refreshLists(){
    if(this.isAdmin){
      this.orderService.listOrders().then(data =>{
        this.orders = data;
      })
    }else{
      this.orderService.listMyorders(this.userId).then(data => {
        this.myOrders = data;
        if(this.myOrders!.filter(o => o.orderStatus==="UNDER_PROCESS").length > 0){
          this.enableOrder = false
        }else{
          this.enableOrder= true
        }
      })
      
    }
  }


  AddToCart(menuItem : Menu){
    if(menuItem.recipeDto != undefined){
      menuItem.menuItemName = menuItem.recipeDto.name;
    }else{
      menuItem.menuItemName = menuItem.itemDto.name;
    }
    this.cost += menuItem.price
    this.cart?.push(menuItem);
    console.log(this.cost)
  }

  removeFromCart(item : Menu){
    this.cart.splice(this.cart.indexOf(item), 1);
    this.cost -= item.price;
  }

  openCheckOutModal(){
    if(this.cart.length === 0){
      alert("Your cart is empty!")
      return;
    }
    const modalRef = this.modalService.open(OrderModalComponent, {size: 'm', scrollable: true});
    modalRef.componentInstance.cart = this.cart;
    modalRef.componentInstance.cost = this.cost;
    modalRef.closed.toPromise().then( result =>{
      this.cost = 0.0;
      this.cart = [];
      this.refreshLists();
    })
  }
  getMenuItemName(menu : Menu){
    const menuItem = this.menuItems?.find(m => m.id === menu.id)
    if(menuItem!.recipeDto != null){
      return menuItem!.recipeDto.name;
    }
    else{
      return menuItem!.itemDto.name;
    }
  }

  closeOrder(order : Order){
      this.orders![this.orders!.indexOf(order)].orderStatus ="CLOSED";
      this.orderService.closeOrder(order.id);      
  }

  toggle(food : Menu){
    food.isCollapsed = !food.isCollapsed
  }

  deleteOrder(order : Order){
    this.orderService.deleteOreder(order.id).then(result =>{
          this.orders?.splice(this.orders.indexOf(order), 1)
        })
  }
}
