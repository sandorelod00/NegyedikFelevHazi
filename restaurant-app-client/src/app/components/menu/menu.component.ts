import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Item } from 'src/app/models/itemDto';
import { Menu } from 'src/app/models/menuDto';
import { Recipe } from 'src/app/models/recipeDto';
import { MenuService } from 'src/app/services/menu/menu.service';
import { RecipeService } from 'src/app/services/recipe/recipe.service';
import { StorageService } from 'src/app/services/storage/storage.service';
import { MenuModalComponent } from './menu-modal/menu-modal.component';
import {MenuItemType} from './MenuItemType';

@Component({
  selector: 'restaurant-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private menuService : MenuService, private modalService : NgbModal, private recipeService : RecipeService, private storageService : StorageService) { }

  isRender! : boolean;
  
  recipes : Recipe[] | undefined;
  drinks : Item[] | undefined;

  menuItems : Menu[] | undefined;
  foods : Menu[] | undefined;
  alcoholicDrinks : Menu[] |undefined;
  nonAlcoholicDrinks : Menu[] | undefined;


  async ngOnInit(): Promise<void> {
    this.isRender = true;
    await this.menuService.getMenu().then(m => this.menuItems = m);
    await this.storageService.getDrinks().then(d => this.drinks = d);
    await this.recipeService.getAllRecipe().then(r => this.recipes = r);

    this.foods = this.menuItems?.filter(m => m.recipeId != null);
    this.alcoholicDrinks = this.menuItems?.filter(m => m.itemId != null && m.itemDto.itemType === "ALCOHOLIC_DRINK" );
    this.nonAlcoholicDrinks = this.menuItems?.filter(m => m.itemId != null && m.itemDto.itemType == "NON_ALCOHOLIC_DRINK");
    this.isRender = false;
  }

  openAddNewMenuItemModa(onMenuItemList : Menu[] | undefined, menuItemType : number){
    const modalRef = this.modalService.open(MenuModalComponent, {size: 'xl', scrollable: true});
    modalRef.componentInstance.onMenuItemList = onMenuItemList;
    
    if(menuItemType === 0){
      modalRef.componentInstance.recipeList = this.recipes;
      modalRef.componentInstance.menuItemType = MenuItemType.Food;
    }
    if(menuItemType === 1){
      modalRef.componentInstance.nonAlcoholicDrinkList = this.drinks?.filter(d => d.itemType === "NON_ALCOHOLIC_DRINK");
      modalRef.componentInstance.menuItemType = MenuItemType.NonAlcoholicDrink;
    }
    if(menuItemType === 2){
      modalRef.componentInstance.alcoholicDrinkList = this.drinks?.filter(d => d.itemType === "ALCOHOLIC_DRINK");
      modalRef.componentInstance.menuItemType = MenuItemType.AlcoholicDrink;
    }
  }

  editLine(menuItem : Menu){
    menuItem.isEdit = true;
    menuItem.profitPercent *= 100
  }

  saveLine(menuItem : Menu){
    menuItem.isEdit = false;
    menuItem.profitPercent /= 100
    if(menuItem.recipeDto != null){
      menuItem.price = menuItem.recipeDto.price * menuItem.profitPercent + menuItem.recipeDto.price
    }
    else{
      menuItem.price = menuItem.itemDto.price * menuItem.profitPercent + menuItem.itemDto.price
    }
    this.menuService.updateMenuItem(menuItem);
  }

}
