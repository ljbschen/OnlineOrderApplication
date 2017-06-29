"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var restaurant_service_1 = require("./restaurant.service");
var AppComponent = (function () {
    function AppComponent(restaurantService) {
        this.restaurantService = restaurantService;
        this.title = 'Online Order Application';
    }
    AppComponent.prototype.onSelect = function (restaurant) {
        this.selectedRestaurant = restaurant;
    };
    AppComponent.prototype.getRestaurants = function () {
        var _this = this;
        this.restaurantService.getRestaurants().then(function (restaurants) { return _this.restaurants = restaurants; });
    };
    AppComponent.prototype.ngOnInit = function () {
        this.getRestaurants();
    };
    return AppComponent;
}());
AppComponent = __decorate([
    core_1.Component({
        selector: 'my-app',
        template: "\n    <h1>{{title}}</h1>\n    <h2>My Restaurant</h2>\n    <ul class=\"restaurants\">\n      <li *ngFor=\"let restaurant of restaurants\" \n        [class.selected]=\"restaurant === selectedRestaurant\"\n        (click)=\"onSelect(restaurant)\">\n        <span class=\"badge\">{{restaurant.id}}</span> {{restaurant.name}}\n      </li>\n    </ul>\n    <restaurant-detail [restaurant]=\"selectedRestaurant\"></restaurant-detail>\n  ",
        styles: ["\n    .selected {\n      background-color: #CFD8DC !important;\n      color: white;\n    }\n    .restaurants {\n      margin: 0 0 2em 0;\n      list-style-type: none;\n      padding: 0;\n      width: 15em;\n    }\n    .restaurants li {\n      cursor: pointer;\n      position: relative;\n      left: 0;\n      background-color: #EEE;\n      margin: .5em;\n      padding: .3em 0;\n      height: 1.6em;\n      border-radius: 4px;\n    }\n    .restaurants li.selected:hover {\n      background-color: #BBD8DC !important;\n      color: white;\n    }\n    .restaurants li:hover {\n      color: #607D8B;\n      background-color: #DDD;\n      left: .1em;\n    }\n    .restaurants .text {\n      position: relative;\n      top: -3px;\n    }\n    .restaurants .badge {\n      display: inline-block;\n      font-size: small;\n      color: white;\n      padding: 0.8em 0.7em 0 0.7em;\n      background-color: #607D8B;\n      line-height: 1em;\n      position: relative;\n      left: -1px;\n      top: -4px;\n      height: 1.8em;\n      margin-right: .8em;\n      border-radius: 4px 0 0 4px;\n    }\n  "],
        providers: [restaurant_service_1.RestaurantService]
    }),
    __metadata("design:paramtypes", [restaurant_service_1.RestaurantService])
], AppComponent);
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map