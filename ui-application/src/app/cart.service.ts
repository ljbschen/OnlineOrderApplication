import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { CartItem } from './cartItem';
import { Item } from './item';
import {CartEvent} from "./cartEvent";

@Injectable()
export class CartService {
  private cartUrl = 'http://localhost:8080/cart-service/cart';  // URL to web api

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

  // update(restaurant: Restaurant): Promise<Restaurant> {
  //   const url = `${this.restaurantsUrl}/${restaurant.id}`;
  //   return this.http
  //     .put(url, JSON.stringify(restaurant), {headers: this.headers})
  //     .toPromise()
  //     .then(() => restaurant)
  //     .catch(this.handleError);
  // }
  //
  // create(name: string): Promise<Restaurant> {
  //   return this.http
  //     .post(this.restaurantsUrl, JSON.stringify({name: name}), {headers: this.headers})
  //     .toPromise()
  //     .then(res => res.json().data as Restaurant)
  //     .catch(this.handleError);
  // }

  delete(name: string): Promise<void> {
    return null;
  }

  addItem(selectedItem: Item): Promise<number> {
    const url = `${this.cartUrl}/1/events`;
    console.log(url);
    let data = new CartEvent;
    data.cartEventType = 'ADD_ITEM';
    // data.item = new CartItem;
    // data.item.itemRestaurantName = selectedItem.itemRestaurantName;
    // data.item.itemName = selectedItem.itemName;
    // data.item.itemPrice = selectedItem.itemPrice;
    console.info(JSON.stringify(data));
    // return this.http.get(url)
    //   .toPromise()
    //   .then(response => {
    //     // response.json().data as CartItem[];
    //     console.info(response.status.toString());
    //   })
    //   .catch(this.handleError);
    return this.http.post(url, data)
      .toPromise()
      .then(response => response.status as number)
      .catch(this.handleError);
  }

}
