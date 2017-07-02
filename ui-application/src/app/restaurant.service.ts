import { Injectable } from '@angular/core';
import { Restaurant } from './restaurant';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class RestaurantService {
  private restaurantsUrl = 'http://localhost:8080/menu-service/restaurants';  // URL to web api

  constructor(private http: Http) { }

  getRestaurants(): Promise<Restaurant[]> {
    return this.http.get(this.restaurantsUrl)
      .toPromise()
      .then(response => response.json().content as Restaurant[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  getRestaurant(id: string): Promise<Restaurant> {
    const url = `${this.restaurantsUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Restaurant)
      .catch(this.handleError);
  }

  private headers = new Headers({'Content-Type': 'application/json'});

  delete(id: number): Promise<void> {
    const url = `${this.restaurantsUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }
}
