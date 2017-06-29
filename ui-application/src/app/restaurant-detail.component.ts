import { Component, Input } from '@angular/core';
import { Restaurant } from './restaurant';

@Component({
  selector: 'restaurant-detail',
  template: `
  <div *ngIf="restaurant">
      <h2>{{restaurant.name}} details!</h2>
      <div><label>id: </label>{{restaurant.id}}</div>
      <div>
        <label>name: </label>
        <input [(ngModel)]="restaurant.name" placeholder="name"/>
      </div>
     </div>`
})

export class RestaurantDetailComponent {
  @Input() restaurant: Restaurant;
}
