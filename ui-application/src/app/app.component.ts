import { Component } from "@angular/core";

@Component ({
  selector: 'my-app',
  styleUrls: ['./app.component.css'],
  template: `
    <h1>{{title}}</h1>
    <nav>
     <!--<a routerLink="/dashboard">Dashboard</a>-->
     <a routerLink="/restaurants">Restaurants</a>
   </nav>
    <router-outlet>
    `
})

export class AppComponent {
  title = 'Online Order Application';
}
