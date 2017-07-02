import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';
import { Restaurant } from './restaurant';
import { CartService } from './cart.service';
import { RestaurantService } from './restaurant.service';
import { Item } from './item';

import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'restaurant-detail',
  templateUrl: './restaurant-detail.component.html',
  styleUrls: [ './restaurant-detail.component.css' ]
})

export class RestaurantDetailComponent implements OnInit{
  @Input() restaurant: Restaurant;
  @Input() items: Item[];
  selectedItem: Item;

  constructor (
    private restaurantService: RestaurantService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.restaurantService.getRestaurant(params.get("restaurantName")))
      .subscribe(result => {
        console.log(result);
        this.restaurant = result;
        this.items = this.restaurant.menu;
      });
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.cartService.addItem(this.selectedItem)
      .then(() => this.goBack());
  }

  onSelect(item: Item): void {
    this.selectedItem = item;
  }

  add(): void {
    window.alert("add " + this.selectedItem.itemName + " successfully");
  }
}
