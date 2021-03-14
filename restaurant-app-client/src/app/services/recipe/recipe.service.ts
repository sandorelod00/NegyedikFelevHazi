import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recipe } from 'src/app/models/recipeDto';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private http: HttpClient) { }

  getAllRecipe(): Promise<Recipe[]>{
    return this.http.get<Recipe[]>('api/recipe/getallrecipe').toPromise()
  }

  updateRecipe(result: Recipe) : Promise<any> {
    return this.http.put('api/recipe/updaterecipe', result).toPromise();
  }
  
  saveNewRecipe(result: Recipe) : Promise<any> {
    return this.http.post('api/recipe/addnewrecipe', result).toPromise();
  }

  deleteRecipe(id : number) : Promise<any>{
    return this.http.delete('api/recipe/deleterecipe?id=' + id).toPromise();
  }

  deleteIngredient(id : number) : Promise<any>{
    return this.http.delete('api/recipe/deleteingredientbyid?id='+id).toPromise();
  }
}
