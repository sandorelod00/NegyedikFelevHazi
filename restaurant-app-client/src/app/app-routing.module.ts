import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { OrderComponent } from './components/order/order.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { StorageComponent } from './components/storage/storage.component';
import { TableComponent } from './components/table/table.component';

const routes: Routes = [{
  path: '', redirectTo: '/login', pathMatch: 'full'
},
{
  path: 'menu',
  component: MenuComponent
},
{
  path: 'order',
  component: OrderComponent
},
{
  path: 'recipe',
  component: RecipeComponent
},
{
  path: 'storage',
  component: StorageComponent
},
{
  path: 'table',
  component: TableComponent
},
{
  path: 'login',
  component: LoginComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
