import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';

// Observable class extensions
import 'rxjs/add/observable/of';

// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

import { RestaurantSearchService } from './restaurant-search.service';
import { Restaurant } from './restaurant';

@Component({
  selector: 'restaurant-search',
  templateUrl: './restaurant-search.component.html',
  styleUrls: [ './restaurant-search.component.css' ],
  providers: [RestaurantSearchService]
})
export class RestaurantSearchComponent implements OnInit {
  restaurants: Observable<Restaurant[]>;
  private searchTerms = new Subject<string>();

  constructor(
    private restaurantSearchService: RestaurantSearchService,
    private router: Router) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.restaurants = this.searchTerms
      .debounceTime(300)        // wait 300ms after each keystroke before considering the term
      .distinctUntilChanged()   // ignore if next search term is same as previous
      .switchMap(term => term   // switch to new observable each time the term changes
        // return the http search observable
        ? this.restaurantSearchService.search(term)
        // or the observable of empty heroes if there was no search term
        : Observable.of<Restaurant[]>([]))
      .catch(error => {
        // TODO: add real error handling
        console.log(error);
        return Observable.of<Restaurant[]>([]);
      });
  }

  gotoDetail(restaurant: Restaurant): void {
    let link = ['/detail', restaurant.id];
    this.router.navigate(link);
  }
}
