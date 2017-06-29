import { Component } from '@angular/core';
import { Restaurant } from './restaurant';
import { RestaurantService } from './restaurant.service';
import { OnInit } from '@angular/core';
import {RESTAURANTS} from "./mock-restaurants";

@Component({
  selector: 'my-app',
  template: `
    <h1>{{title}}</h1>
    <h2>My Restaurant</h2>
    <ul class="restaurants">
      <li *ngFor="let restaurant of restaurants" 
        [class.selected]="restaurant === selectedRestaurant"
        (click)="onSelect(restaurant)">
        <span class="badge">{{restaurant.id}}</span> {{restaurant.name}}
      </li>
    </ul>
    <restaurant-detail [restaurant]="selectedRestaurant"></restaurant-detail>
  `,
  styles: [`
    .selected {
      background-color: #CFD8DC !important;
      color: white;
    }
    .restaurants {
      margin: 0 0 2em 0;
      list-style-type: none;
      padding: 0;
      width: 15em;
    }
    .restaurants li {
      cursor: pointer;
      position: relative;
      left: 0;
      background-color: #EEE;
      margin: .5em;
      padding: .3em 0;
      height: 1.6em;
      border-radius: 4px;
    }
    .restaurants li.selected:hover {
      background-color: #BBD8DC !important;
      color: white;
    }
    .restaurants li:hover {
      color: #607D8B;
      background-color: #DDD;
      left: .1em;
    }
    .restaurants .text {
      position: relative;
      top: -3px;
    }
    .restaurants .badge {
      display: inline-block;
      font-size: small;
      color: white;
      padding: 0.8em 0.7em 0 0.7em;
      background-color: #607D8B;
      line-height: 1em;
      position: relative;
      left: -1px;
      top: -4px;
      height: 1.8em;
      margin-right: .8em;
      border-radius: 4px 0 0 4px;
    }
  `],
  providers: [RestaurantService]
})

export class AppComponent implements OnInit {
  title = 'Online Order Application';
  restaurants: Restaurant[];
  selectedRestaurant: Restaurant;

  constructor(private restaurantService: RestaurantService) { }

  onSelect(restaurant: Restaurant): void {
    this.selectedRestaurant = restaurant;
  }

  getRestaurants(): void {
    this.restaurantService.getRestaurants().then(restaurants => this.restaurants = restaurants);
  }

  ngOnInit(): void {
    this.getRestaurants();
  }
}


