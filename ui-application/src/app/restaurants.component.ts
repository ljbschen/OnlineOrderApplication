import { Component } from '@angular/core';
import { Restaurant } from './restaurant';
import { RestaurantService } from './restaurant.service';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'my-restaurants',
  templateUrl: './restaurant.component.html',
  styleUrls: [ './restaurants.component.css' ]
})

export class RestaurantComponent implements OnInit {

  restaurants: Restaurant[];
  selectedRestaurant: Restaurant;

  // injection
  constructor(private restaurantService: RestaurantService, private router: Router) { }

  onSelect(restaurant: Restaurant): void {
    this.selectedRestaurant = restaurant;
  }

  getRestaurants(): void {
    this.restaurantService.getRestaurants()
      .then(restaurants => this.restaurants = restaurants);
  }

  ngOnInit(): void {
    this.getRestaurants();
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectedRestaurant.id]);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.restaurantService.create(name)
      .then(restaurant => {
        this.restaurants.push(restaurant);
        this.selectedRestaurant = null;
      });
  }

  delete(restaurant: Restaurant): void {
    this.restaurantService
      .delete(restaurant.id)
      .then(() => {
        this.restaurants = this.restaurants.filter(h => h !== restaurant);
        if (this.selectedRestaurant === restaurant) { this.selectedRestaurant = null; }
      });
  }
}


