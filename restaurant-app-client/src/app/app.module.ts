import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { EmployeeService } from './services/employee/employee.service';
import {MenuService} from './services/menu/menu.service';
import {OrderService} from './services/order/order.service';
import {RecipeService} from './services/recipe/recipe.service';
import { TableService } from './services/table/table.service';
import { StorageService } from './services/storage/storage.service';

import { EmployeeComponent } from './components/employee/employee.component';
import {MenuComponent} from './components/menu/menu.component';
import { OrderComponent } from './components/order/order.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { StorageComponent } from './components/storage/storage.component';
import { TableComponent } from './components/table/table.component';
import { ItemModalComponent } from './components/storage/item-modal/item-modal.component';
import { RecipeModalComponent } from './components/recipe/recipe-modal/recipe-modal.component';
import { IngredientModalComponent } from './components/ingredient-modal/ingredient-modal.component';
import { MenuModalComponent } from './components/menu/menu-modal/menu-modal.component';
import { OrderModalComponent } from './components/order/order-modal/order-modal.component';
import { LoginComponent } from './components/login/login.component';
import { TokenInterceptorService } from './services/auth/token-interceptor.service';
import { AuthService } from './services/auth/auth.service';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    MenuComponent,
    OrderComponent,
    RecipeComponent,
    StorageComponent,
    TableComponent,
    ItemModalComponent,
    RecipeModalComponent,
    IngredientModalComponent,
    MenuModalComponent,
    OrderModalComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonModule,
    NgbModule
  ],
  providers: [
    EmployeeService,
    MenuService,
    OrderService,
    RecipeService,
    StorageService,
    TableService,
    AuthService,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
