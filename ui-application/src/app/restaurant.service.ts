import { Injectable } from '@angular/core';
import { Restaurant } from './restaurant';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class RestaurantService {
  private restaurantsUrl = 'api/restaurants';  // URL to web api

  constructor(private http: Http) { }

  getRestaurants(): Promise<Restaurant[]> {
    return this.http.get(this.restaurantsUrl)
      .toPromise()
      .then(response => response.json().data as Restaurant[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  getRestaurant(id: number): Promise<Restaurant> {
    const url = `${this.restaurantsUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as Restaurant)
      .catch(this.handleError);
  }

  private headers = new Headers({'Content-Type': 'application/json'});

  update(restaurant: Restaurant): Promise<Restaurant> {
    const url = `${this.restaurantsUrl}/${restaurant.id}`;
    return this.http
      .put(url, JSON.stringify(restaurant), {headers: this.headers})
      .toPromise()
      .then(() => restaurant)
      .catch(this.handleError);
  }

  create(name: string): Promise<Restaurant> {
    return this.http
      .post(this.restaurantsUrl, JSON.stringify({name: name}), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as Restaurant)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.restaurantsUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }
}
