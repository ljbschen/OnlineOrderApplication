import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';

import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { Restaurant }           from './restaurant';

@Injectable()
export class RestaurantSearchService {

  constructor(private http: Http) {}

  search(term: string): Observable<Restaurant[]> {
    return this.http
      .get(`api/restaurants/?name=${term}`)
      .map(response => response.json().data as Restaurant[]);
  }
}
