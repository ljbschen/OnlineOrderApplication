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
var http_1 = require("@angular/http");
require("rxjs/add/operator/toPromise");
var cartEvent_1 = require("./cartEvent");
var CartService = (function () {
    function CartService(http) {
        this.http = http;
        this.cartUrl = 'http://localhost:8080/cart-service/cart'; // URL to web api
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
    }
    CartService.prototype.getItems = function () {
        return this.http.get(this.cartUrl + "/1")
            .toPromise()
            .then(function (response) { return response.json(); })
            .catch(this.handleError);
    };
    CartService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    // update(restaurant: Restaurant): Promise<Restaurant> {
    //   const url = `${this.restaurantsUrl}/${restaurant.id}`;
    //   return this.http
    //     .put(url, JSON.stringify(restaurant), {headers: this.headers})
    //     .toPromise()
    //     .then(() => restaurant)
    //     .catch(this.handleError);
    // }
    //
    // create(name: string): Promise<Restaurant> {
    //   return this.http
    //     .post(this.restaurantsUrl, JSON.stringify({name: name}), {headers: this.headers})
    //     .toPromise()
    //     .then(res => res.json().data as Restaurant)
    //     .catch(this.handleError);
    // }
    CartService.prototype.delete = function (name) {
        return null;
    };
    CartService.prototype.addItem = function (selectedItem) {
        var url = this.cartUrl + "/1/events";
        console.log(url);
        var data = new cartEvent_1.CartEvent;
        data.cartEventType = 'ADD_ITEM';
        // data.item = new CartItem;
        // data.item.itemRestaurantName = selectedItem.itemRestaurantName;
        // data.item.itemName = selectedItem.itemName;
        // data.item.itemPrice = selectedItem.itemPrice;
        console.info(JSON.stringify(data));
        // return this.http.get(url)
        //   .toPromise()
        //   .then(response => {
        //     // response.json().data as CartItem[];
        //     console.info(response.status.toString());
        //   })
        //   .catch(this.handleError);
        return this.http.post(url, data)
            .toPromise()
            .then(function (response) { return response.status; })
            .catch(this.handleError);
    };
    return CartService;
}());
CartService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], CartService);
exports.CartService = CartService;
//# sourceMappingURL=cart.service.js.map