import { Component, OnInit, Input } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import { Item } from 'src/app/models/itemDto';


@Component({
  selector: 'restaurant-item-modal',
  templateUrl: './item-modal.component.html',
  styleUrls: ['./item-modal.component.css']
})
export class ItemModalComponent implements OnInit {

  itemName : string | undefined
  itemTotalQuanity : number | undefined
  itemPrice : number | undefined
  itemType : string | undefined

  constructor(public activeModal : NgbActiveModal) { }

  ngOnInit(): void {
    
  }

  saveNewItem(){
    let item = new Item;
    item.name = this.itemName!;
    item.totalQuantity = this.itemTotalQuanity!;
    item.price = this.itemPrice!;
    item.itemType = this.itemType!;
    this.activeModal.close(item)
  }
}
