import { Item } from './itemDto';
import { Recipe } from './recipeDto';

export class Menu{
    "id" : number;
    "profitPercent" : number;
    "recipeId" : number;
    "itemId" : number;

    "itemDto" : Item;
    "recipeDto" : Recipe;

    "price" : number;

    "isEdit" : boolean = false;

    "menuItemName" : string;

    "isCollapsed" : boolean = true;
}