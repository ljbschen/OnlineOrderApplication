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
var router_1 = require("@angular/router");
var RestaurantComponent = (function () {
    // injection
    function RestaurantComponent(restaurantService, router) {
        this.restaurantService = restaurantService;
        this.router = router;
    }
    RestaurantComponent.prototype.onSelect = function (restaurant) {
        this.selectedRestaurant = restaurant;
    };
    RestaurantComponent.prototype.getRestaurants = function () {
        var _this = this;
        this.restaurantService.getRestaurants()
            .then(function (restaurants) { return _this.restaurants = restaurants; });
    };
    RestaurantComponent.prototype.ngOnInit = function () {
        this.getRestaurants();
    };
    RestaurantComponent.prototype.gotoDetail = function () {
        this.router.navigate(['/detail', this.selectedRestaurant.restaurantName]);
    };
    return RestaurantComponent;
}());
RestaurantComponent = __decorate([
    core_1.Component({
        selector: 'my-restaurants',
        templateUrl: './restaurant.component.html',
        styleUrls: ['./restaurants.component.css']
    }),
    __metadata("design:paramtypes", [restaurant_service_1.RestaurantService, router_1.Router])
], RestaurantComponent);
exports.RestaurantComponent = RestaurantComponent;
//# sourceMappingURL=restaurants.component.js.map