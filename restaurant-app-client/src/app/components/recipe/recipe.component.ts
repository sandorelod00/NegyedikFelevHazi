import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Recipe } from 'src/app/models/recipeDto';
import { RecipeService } from 'src/app/services/recipe/recipe.service';
import { RecipeModalComponent } from './recipe-modal/recipe-modal.component';

@Component({
  selector: 'restaurant-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  constructor(private recipeService : RecipeService, private modalService : NgbModal) { }

  recipes : Recipe[] | undefined

  ngOnInit(): void {
    this.recipeService.getAllRecipe().then(r => this.recipes = r);
  }

  openRecipeModalForm(recipe? : Recipe){
    const modalRef = this.modalService.open(RecipeModalComponent);
    modalRef.componentInstance.recipe = recipe;
    modalRef.closed.toPromise().then(result =>{
      if(result!){
      if(result.isNew){
        this.recipes?.push(result);
        this.recipeService.saveNewRecipe(result);
      }
      else{
        this.recipeService.updateRecipe(result);
      }
    }})

    modalRef.dismissed.toPromise().then(r => console.log(r));
  }

  deleteRecipe(recipe : Recipe){
    this.recipeService.deleteRecipe(recipe.id);
    this.recipes?.splice(this.recipes.indexOf(recipe), 1);
  }
}
