import { Component, OnInit } from '@angular/core';
import { CartService } from './cart.service';
import { CartItem } from './cartItem';
import { Router } from '@angular/router';

@Component({
  selector: 'my-cart',
  templateUrl: './cart.component.html',
  styleUrls: [ './cart.component.css' ]
})

export class CartComponent implements OnInit {
  items: CartItem[];
  selectedItem: CartItem;

  // injection
  constructor(private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    this.getItems();
  }

  onSelect(item: CartItem): void {
    this.selectedItem = item;
  }

  getItems(): void {
    this.cartService.getItems()
      .then(items => {
        this.items = items;
      });
  }

  delete(item: CartItem): void {
    this.cartService.delete(item.itemName)
      .then(() => {
        this.items = this.items.filter(h => h !== item);
        if (this.selectedItem === item) { this.selectedItem = null; }
      });
  }

  checkOut() : void {
    this.cartService.checkOut()
      .then(result => {
        console.info(result);
        if (result.status == 202) {
          alert("order placed successfully");
          // this.router.navigate();
        } else {
          alert("order declined");
          this.router.navigate(['/restaurants']);
        }
      });
  }

}
