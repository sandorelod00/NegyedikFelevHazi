import { Ingredient } from './ingredientDto';

export class Recipe{
    "id" : number;
    "name" : string;
    "price" : number;
    "isAvailable" : boolean;
    "ingredients" : Ingredient[];
    "isNew" : boolean;
}