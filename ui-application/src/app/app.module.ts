import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { RestaurantDetailComponent } from './restaurant-detail.component';
import { AppComponent } from "./app.component";
import { RestaurantComponent } from "./restaurants.component";
import { RestaurantService } from "./restaurant.service";

import { AppRoutingModule }     from './app-routing.module';
import { CartComponent } from './cart.component';
import { CartService } from './cart.service';

@NgModule({
  imports:      [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    RestaurantDetailComponent,
    RestaurantComponent,
    CartComponent
  ],
  providers:    [
    RestaurantService,
    CartService
  ],
  bootstrap:    [ AppComponent ]
})

export class AppModule { }
