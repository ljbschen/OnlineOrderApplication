import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RestaurantComponent }      from './restaurants.component';
import { RestaurantDetailComponent }  from './restaurant-detail.component';
import { CartComponent } from './cart.component';

const routes: Routes = [
  // { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: '', redirectTo: '/restaurants', pathMatch: 'full' },
  { path: 'cart',  component: CartComponent },
  { path: 'detail/:id', component: RestaurantDetailComponent },
  { path: 'restaurants',     component: RestaurantComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
