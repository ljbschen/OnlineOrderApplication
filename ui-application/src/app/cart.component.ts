import { Component, OnInit } from '@angular/core';
import { Item } from './item';
import { Router } from '@angular/router';
import { CartService } from './cart.service';

@Component({
  selector: 'my-items',
  templateUrl: './restaurant.component.html',
  styleUrls: [ './restaurants.component.css' ]
})

export class CartComponent implements OnInit {

  items: Item[];
  selectedItem: Item;

  // injection
  constructor(private cartService: CartService, private router: Router) { }

  onSelect(item: Item): void {
    this.selectedItem = item;
  }

  getItems(): void {
    this.cartService.getItems()
      .then(items => this.items = items);
  }

  ngOnInit(): void {
    this.getItems();
  }

  delete(item: Item): void {
    this.cartService
      .delete(item.name)
      .then(() => {
        this.items = this.items.filter(h => h !== item);
        if (this.selectedItem === item) { this.selectedItem = null; }
      });
  }

}
