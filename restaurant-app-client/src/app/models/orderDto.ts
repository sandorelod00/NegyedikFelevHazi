import { OrderService } from '../services/order/order.service';
import { Menu } from './menuDto';

export class Order{
    "id" : number;
    "orderType" : string;
    "orderStatus" : string;
    "price" : number;
    "customerId" : number;
    "tableId" : number;
    "menuItems" : number[]
    "menuItemsDto" : Menu[]
}