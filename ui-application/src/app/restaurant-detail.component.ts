import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';
import { Restaurant } from './restaurant';
import { RestaurantService}  from './restaurant.service';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'restaurant-detail',
  templateUrl: './restaurant-detail.component.html'
})

export class RestaurantDetailComponent implements OnInit{
  @Input() restaurant: Restaurant;

  constructor (
    private restaurantService: RestaurantService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.restaurantService.getRestaurant(+params.get('id')))
      .subscribe(restaurant => this.restaurant = restaurant);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.restaurantService.update(this.restaurant)
      .then(() => this.goBack());
  }
}
