import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Item } from 'src/app/models/itemDto';
import { Menu } from 'src/app/models/menuDto';
import { Recipe } from 'src/app/models/recipeDto';
import { MenuService } from 'src/app/services/menu/menu.service';
import { MenuItemType } from '../MenuItemType';

@Component({
  selector: 'restaurant-menu-modal',
  templateUrl: './menu-modal.component.html',
  styleUrls: ['./menu-modal.component.css']
})
export class MenuModalComponent implements OnInit {

  constructor(public activeModal : NgbActiveModal, private menuService : MenuService) { }


  @Input() public onMenuItemList : Menu[] | undefined;
  @Input() public menuItemType! : MenuItemType;

  title : string | undefined;

  @Input() recipeList : Recipe[] | undefined;
  @Input()  alcoholicDrinkList : Item[] | undefined;
  @Input() nonAlcoholicDrinkList : Item[] | undefined

  ngOnInit(): void {
    const enumVal = this.menuItemType;
    if(enumVal === MenuItemType.Food){
      this.title = "food"
      this.onMenuItemList?.forEach(menuItem => {
        this.recipeList?.forEach(recipe => {
          if(recipe.id === menuItem.recipeId){
            this.recipeList?.splice(this.recipeList.indexOf(recipe), 1);
          }
        })
      });
    }
    if(enumVal  === MenuItemType.NonAlcoholicDrink){
      this.title = "non alcoholic drink";
      this.onMenuItemList?.forEach(menuItem => {
        this.nonAlcoholicDrinkList?.forEach(drink => {
          if(drink.id === menuItem.itemId){
            this.nonAlcoholicDrinkList?.splice(this.nonAlcoholicDrinkList.indexOf(drink), 1);
          }
        })
      });
    }
    if(enumVal  === MenuItemType.AlcoholicDrink){
      this.title = "alcoholic drink";
      this.onMenuItemList?.forEach(menuItem => {
        this.alcoholicDrinkList?.forEach(drink => {
          if(drink.id === menuItem.itemId){
            this.alcoholicDrinkList?.splice(this.alcoholicDrinkList.indexOf(drink), 1);
          }
        })
      });
    }
  }

  removeFromMenu(item : Menu){ 
    this.menuService.removeMenuItem(item.id).then(result => {
      const enumVal = this.menuItemType;
      this.onMenuItemList?.splice(this.onMenuItemList.indexOf(item), 1);
      if(enumVal === MenuItemType.Food){
        this.recipeList?.push(item.recipeDto);
      }
      if(enumVal === MenuItemType.NonAlcoholicDrink){
        this.nonAlcoholicDrinkList?.push(item.itemDto);
      }
      if(enumVal === MenuItemType.AlcoholicDrink){
        this.alcoholicDrinkList?.push(item.itemDto)
      }
      if(item.id === undefined){
        return;
      }
    })
    .catch(error =>{
      alert("You can't remove Menu Item untill you have opened oreders with this item.")
    }
    );
    
  }

  addToMenu(id : number){
    const enumVal = this.menuItemType;
    let newMenuItem =new Menu;
    if(enumVal === MenuItemType.Food){
      let recipe =  this.recipeList?.find(r => r.id === id);
      this.recipeList?.splice(this.recipeList.indexOf(recipe!), 1)
      newMenuItem.recipeId = recipe!.id;
      newMenuItem.recipeDto = recipe!;
    }
    if(enumVal === MenuItemType.NonAlcoholicDrink){
      let drink = this.nonAlcoholicDrinkList?.find(drink => drink.id === id);
      this.nonAlcoholicDrinkList?.splice(this.nonAlcoholicDrinkList.indexOf(drink!), 1);
      newMenuItem.itemId = drink!.id;
      newMenuItem.itemDto = drink!;
    }
    if(enumVal === MenuItemType.AlcoholicDrink){
      let drink = this.alcoholicDrinkList?.find(drink => drink.id === id);
      this.alcoholicDrinkList?.splice(this.alcoholicDrinkList.indexOf(drink!), 1);
      newMenuItem.itemId = drink!.id;
      newMenuItem.itemDto = drink!;
    }
    newMenuItem.profitPercent = 0.05
    this.onMenuItemList?.push(newMenuItem);
    this.menuService.addMenuItem(newMenuItem);
  }

}
