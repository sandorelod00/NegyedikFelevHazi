import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Ingredient } from 'src/app/models/ingredientDto';
import { Item } from 'src/app/models/itemDto';
import { StorageService } from 'src/app/services/storage/storage.service';

@Component({
  selector: 'restaurant-ingredient-modal',
  templateUrl: './ingredient-modal.component.html',
  styleUrls: ['./ingredient-modal.component.css']
})
export class IngredientModalComponent implements OnInit {

  constructor(public activeModal : NgbActiveModal, private storageService : StorageService) { }

  items :  Item[] | undefined;

  selectedItem : Item |undefined;
  selectedItemName : string | undefined;
  
  newName : string | undefined;

  neededQuantity : number | undefined;

  ngOnInit(): void {
    this.storageService.getAllItemInStorage().then(i => this.items = i); 
  }


  saveIngredient(){
    let newIngredient = new Ingredient;
    newIngredient.quantityNeeded = this.neededQuantity!;
    if(this.selectedItemName === "new"){
      newIngredient.itemName = this.newName!;
      newIngredient.isAvailable = false;
      newIngredient.price = 0.0;
      newIngredient.isNew = true;
    }
    else{
      let item = this.items?.find(i => i.name === this.selectedItemName)
      newIngredient.itemId = item!.id;
      newIngredient.itemName = item!.name;
      newIngredient.price = item!.price * newIngredient.quantityNeeded;
      item!.totalQuantity < newIngredient.quantityNeeded ? newIngredient.isAvailable = false : newIngredient.isAvailable = true; 
    }
    this.activeModal.close(newIngredient);
  }
}
