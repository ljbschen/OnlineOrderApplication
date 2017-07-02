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
    this.router.navigate(['/detail', this.selectedRestaurant.restaurantName]);
  }
}


