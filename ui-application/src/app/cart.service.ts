import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { CartItem } from './cartItem';
import { Item } from './item';
import { CartEvent } from './cartEvent';
import { Order } from './order';

@Injectable()
export class CartService {
  private cartUrl = 'http://localhost:9003/cart';  // URL to web api

  constructor(private http: Http) { }

  getItems(): Promise<CartItem[]> {
    return this.http.get(`${this.cartUrl}/1`)
      .toPromise()
      .then(response => response.json() as CartItem[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  private headers = new Headers({'Content-Type': 'application/json'});

  delete(name: string): Promise<void> {
    return null;
  }

  addItem(selectedItem: Item): Promise<number> {
    const url = `${this.cartUrl}/1/events`;
    let data = new CartEvent;
    data.cartEventType = 'ADD_ITEM';
    data.item = new CartItem;
    data.item.itemRestaurantName = selectedItem.itemRestaurantName;
    data.item.itemName = selectedItem.itemName;
    data.item.itemPrice = selectedItem.itemPrice;
    return this.http.post(url, data, {headers: this.headers})
      .toPromise()
      .then(response => response.status as number)
      .catch(this.handleError);
  }

  checkOut() : Promise<Response> {
    const url = `${this.cartUrl}/1/checkout`;
    let order = new Order;
    order.shippingAddress = '123 main street';
    order.orderNote = 'no spicy';
    console.info(order);
    return this.http.post(url, order, {headers: this.headers})
      .toPromise()
      .then(response => response)
      .catch(this.handleError);
  }
}
