import { Component, NgModuleRef, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Item } from 'src/app/models/itemDto';
import { StorageService } from 'src/app/services/storage/storage.service';
import { ItemModalComponent } from './item-modal/item-modal.component';

@Component({
  selector: 'restaurant-storage',
  templateUrl: './storage.component.html',
  styleUrls: ['./storage.component.css']
})
export class StorageComponent implements OnInit {

  constructor(private storageService : StorageService, private modalService : NgbModal) { }

  items : Item[] | undefined
  

  ngOnInit(): void {
    this.storageService.getAllItemInStorage().then(i => this.items = i);
  }

  editableLine(item: Item){
    item.isEdit = true;
  }

  saveItem(item : Item){
    item.isEdit = false;
    item.totalPrice = item.price * item.totalQuantity;
    this.storageService.saveItem(item);
  }

  deleteItem(item : Item){
    this.items?.splice(this.items?.indexOf(item), 1);
    this.storageService.deleteItem(item.id);
  }

  openItemModalForm(){
    const modalRef = this.modalService.open(ItemModalComponent);
    let newItem = new Item;
    modalRef.closed.toPromise().then(result => {
      if(result!){
        newItem = result;
        this.storageService.saveNewItem(newItem);
        newItem.totalPrice = newItem.price * newItem.totalQuantity;
        this.items?.push(newItem);
      }
    });

    modalRef.dismissed.toPromise().then(r => console.log(r));
  }

}
