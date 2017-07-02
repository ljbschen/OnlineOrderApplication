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
        this.restaurant = result;
        this.items = this.restaurant.menu;
      });
  }

  goBack(): void {
    this.location.back();
  }

  onSelect(item: Item): void {
    this.selectedItem = item;
  }

  add(): void {
    // post request to cart service
    if (this.selectedItem != null) {
      let item = this.selectedItem;
      item.itemRestaurantName = this.restaurant.restaurantName;
      let status = this.cartService.addItem(item);
      console.log(status);
      window.alert("add " + this.selectedItem.itemName + " successfully");
    } else {
      window.alert("Please select an item first");
    }
  }
}
