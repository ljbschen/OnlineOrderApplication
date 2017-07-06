import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RestaurantComponent }      from './restaurants.component';
import { RestaurantDetailComponent }  from './restaurant-detail.component';
import { CartComponent } from './cart.component';

const routes: Routes = [
  { path: '', redirectTo: '/restaurants', pathMatch: 'full' },
  { path: 'cart', component: CartComponent },
  { path: 'detail/:restaurantName', component: RestaurantDetailComponent },
  { path: 'restaurants', component: RestaurantComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
