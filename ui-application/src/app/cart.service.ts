import { Injectable } from '@angular/core';
import { Restaurant } from './restaurant';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Item } from './item';

@Injectable()
export class CartService {
  private restaurantsUrl = 'api/restaurants';  // URL to web api

  constructor(private http: Http) { }

  getItems(): Promise<Item[]> {
    return this.http.get(this.restaurantsUrl)
      .toPromise()
      .then(response => response.json().data as Item[])
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
    const url = `${this.restaurantsUrl}/${name}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  addItem(selectedItem: Item): Promise<void> {
    const url = `${this.restaurantsUrl}/${name}`;
    return this.http.post(url, {body: selectedItem})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

}
