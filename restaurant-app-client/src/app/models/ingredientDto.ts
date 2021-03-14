export class Ingredient{
    "id" : number;
    "recipeId" : number;
    "quantityNeeded" : number;
    "itemId" : number;
    "itemName" : string;
    "price" : number;
    "isAvailable" : boolean;
    "isEdit" : boolean = false;
    "isNew" : boolean = false;
}