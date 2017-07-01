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

import {DashboardComponent} from "./dashboard.component";

import { AppRoutingModule }     from './app-routing.module';
import { RestaurantSearchComponent } from './restaurant-search.component';

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
    DashboardComponent,
    RestaurantDetailComponent,
    RestaurantComponent,
    RestaurantSearchComponent
  ],
  providers:    [
    RestaurantService
  ],
  bootstrap:    [ AppComponent ]
})

export class AppModule { }
