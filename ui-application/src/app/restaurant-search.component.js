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
var Observable_1 = require("rxjs/Observable");
var Subject_1 = require("rxjs/Subject");
// Observable class extensions
require("rxjs/add/observable/of");
// Observable operators
require("rxjs/add/operator/catch");
require("rxjs/add/operator/debounceTime");
require("rxjs/add/operator/distinctUntilChanged");
var restaurant_search_service_1 = require("./restaurant-search.service");
var RestaurantSearchComponent = (function () {
    function RestaurantSearchComponent(restaurantSearchService, router) {
        this.restaurantSearchService = restaurantSearchService;
        this.router = router;
        this.searchTerms = new Subject_1.Subject();
    }
    // Push a search term into the observable stream.
    RestaurantSearchComponent.prototype.search = function (term) {
        this.searchTerms.next(term);
    };
    RestaurantSearchComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.restaurants = this.searchTerms
            .debounceTime(300) // wait 300ms after each keystroke before considering the term
            .distinctUntilChanged() // ignore if next search term is same as previous
            .switchMap(function (term) { return term // switch to new observable each time the term changes
            ? _this.restaurantSearchService.search(term)
            : Observable_1.Observable.of([]); })
            .catch(function (error) {
            // TODO: add real error handling
            console.log(error);
            return Observable_1.Observable.of([]);
        });
    };
    RestaurantSearchComponent.prototype.gotoDetail = function (restaurant) {
        var link = ['/detail', restaurant.id];
        this.router.navigate(link);
    };
    return RestaurantSearchComponent;
}());
RestaurantSearchComponent = __decorate([
    core_1.Component({
        selector: 'restaurant-search',
        templateUrl: './restaurant-search.component.html',
        styleUrls: ['./restaurant-search.component.css'],
        providers: [restaurant_search_service_1.RestaurantSearchService]
    }),
    __metadata("design:paramtypes", [restaurant_search_service_1.RestaurantSearchService,
        router_1.Router])
], RestaurantSearchComponent);
exports.RestaurantSearchComponent = RestaurantSearchComponent;
//# sourceMappingURL=restaurant-search.component.js.map