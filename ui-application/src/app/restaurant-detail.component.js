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
var router_1 = require("@angular/router");
var common_1 = require("@angular/common");
var restaurant_1 = require("./restaurant");
var restaurant_service_1 = require("./restaurant.service");
require("rxjs/add/operator/switchMap");
var RestaurantDetailComponent = (function () {
    function RestaurantDetailComponent(restaurantService, route, location) {
        this.restaurantService = restaurantService;
        this.route = route;
        this.location = location;
    }
    RestaurantDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.paramMap
            .switchMap(function (params) { return _this.restaurantService.getRestaurant(+params.get('id')); })
            .subscribe(function (restaurant) { return _this.restaurant = restaurant; });
    };
    RestaurantDetailComponent.prototype.goBack = function () {
        this.location.back();
    };
    RestaurantDetailComponent.prototype.save = function () {
        var _this = this;
        this.restaurantService.update(this.restaurant)
            .then(function () { return _this.goBack(); });
    };
    return RestaurantDetailComponent;
}());
__decorate([
    core_1.Input(),
    __metadata("design:type", restaurant_1.Restaurant)
], RestaurantDetailComponent.prototype, "restaurant", void 0);
RestaurantDetailComponent = __decorate([
    core_1.Component({
        selector: 'restaurant-detail',
        templateUrl: './restaurant-detail.component.html'
    }),
    __metadata("design:paramtypes", [restaurant_service_1.RestaurantService,
        router_1.ActivatedRoute,
        common_1.Location])
], RestaurantDetailComponent);
exports.RestaurantDetailComponent = RestaurantDetailComponent;
//# sourceMappingURL=restaurant-detail.component.js.map