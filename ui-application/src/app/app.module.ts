import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service';

import { RestaurantDetailComponent } from './restaurant-detail.component';
import { AppComponent } from "./app.component";
import { RestaurantComponent } from "./restaurants.component";
import { RestaurantService } from "./restaurant.service";

import { AppRoutingModule }     from './app-routing.module';
import { RestaurantSearchComponent } from './restaurant-search.component';
import { CartComponent } from './cart.component';
import { CartService } from './cart.service';

@NgModule({
  imports:      [
    BrowserModule,
    FormsModule,
    HttpModule,
    InMemoryWebApiModule.forRoot(InMemoryDataService),
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    RestaurantDetailComponent,
    RestaurantComponent,
    RestaurantSearchComponent,
    CartComponent
  ],
  providers:    [
    RestaurantService,
    CartService
  ],
  bootstrap:    [ AppComponent ]
})

export class AppModule { }
