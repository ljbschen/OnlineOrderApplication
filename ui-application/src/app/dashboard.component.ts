import {Component, OnInit} from '@angular/core';
import { Restaurant } from './restaurant';
import { RestaurantService } from './restaurant.service';

@Component({
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
  // template: '<h3>My Dashboard</h3>'
})
export class DashboardComponent implements OnInit {

  restaurants: Restaurant[] = [];

  constructor(private restaurantService: RestaurantService) {
  }

  ngOnInit(): void {
    this.restaurantService.getRestaurants()
      .then(restaurants => this.restaurants = restaurants.slice(1, 6));
  }
}
