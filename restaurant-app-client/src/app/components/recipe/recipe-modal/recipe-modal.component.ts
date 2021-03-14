import { Component, OnInit, Input} from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Ingredient } from 'src/app/models/ingredientDto';
import { Item } from 'src/app/models/itemDto';
import { Recipe } from 'src/app/models/recipeDto';
import { RecipeService } from 'src/app/services/recipe/recipe.service';
import { StorageService } from 'src/app/services/storage/storage.service';
import { IngredientModalComponent } from '../../ingredient-modal/ingredient-modal.component';


@Component({
  selector: 'restaurant-recipe-modal',
  templateUrl: './recipe-modal.component.html',
  styleUrls: ['./recipe-modal.component.css']
})
export class RecipeModalComponent implements OnInit {

  constructor(public activeModal : NgbActiveModal, private storageService : StorageService,private modalService : NgbModal, private recipeService : RecipeService) { }

  @Input() public recipe : Recipe | undefined
  
  public isNew : boolean | undefined;
  public isEdit : boolean | undefined;
  ngOnInit(): void {
    if(this.recipe == undefined){
      this.recipe = new Recipe();
      this.recipe.ingredients = [];
      this.recipe.isNew = true;
      this.isNew = true;
    }
    else{
      this.isNew = false;
      this.recipe.isNew = false;
      this.isEdit = false;
    }
  }


  editLine(ingredient : Ingredient){
    ingredient.isEdit = true;
    this.isEdit = true;
  }

  async saveIngredientChange(ingredient : Ingredient){
    ingredient.isEdit = false;
    let item : Item;
    await this.storageService.getItemById(ingredient.itemId).then(r => item = r);
    item!.totalQuantity < ingredient.quantityNeeded ? ingredient.isAvailable = false : ingredient.isAvailable = true;
    ingredient.price = ingredient.quantityNeeded * item!.price;
    this.refreshData();
  }

  removeIngredientFromRecipe(ingredient : Ingredient){
    this.recipe?.ingredients.splice(this.recipe.ingredients.indexOf(ingredient), 1);
    if(ingredient.id != undefined){
      this.recipeService.deleteIngredient(ingredient.id);
  }
    this.refreshData();
  }

  saveRecepie(){
    this.isEdit = false;
    this.activeModal.close(this.recipe);
  }

  openIngredientModal(){
    const modalRef = this.modalService.open(IngredientModalComponent);
    modalRef.closed.toPromise().then(r =>{
      console.log(r);
      this.recipe!.ingredients.push(r);
      this.refreshData();
      this.isEdit = true;
    });
    modalRef.dismissed.toPromise().then(() => {
      this.refreshData();
    })

  }

  refreshData(){
    let sum = 0.0;
    this.recipe!.ingredients.forEach(i => sum += i.price);
    this.recipe!.price = sum;
    this.recipe!.ingredients.filter(i => i.isAvailable == false).length > 0 ? this.recipe!.isAvailable = false : this.recipe!.isAvailable = true;
  }

}
