import { Component, OnInit } from '@angular/core';
import { CartService } from './cart.service';
import { CartItem } from './cartItem';

@Component({
  selector: 'my-cart',
  templateUrl: './cart.component.html',
  styleUrls: [ './cart.component.css' ]
})

export class CartComponent implements OnInit {
  items: CartItem[];
  selectedItem: CartItem;

  // injection
  constructor(private cartService: CartService) { }

  onSelect(item: CartItem): void {
    this.selectedItem = item;
  }

  getItems(): void {
    this.cartService.getItems()
      .then(items => {
        this.items = items;
      });
  }

  ngOnInit(): void {
    this.getItems();
  }

  delete(item: CartItem): void {
    this.cartService.delete(item.itemName)
      .then(() => {
        this.items = this.items.filter(h => h !== item);
        if (this.selectedItem === item) { this.selectedItem = null; }
      });
  }

  CheckOut() : void {

  }

}
