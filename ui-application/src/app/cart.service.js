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
var CartService = (function () {
    function CartService(http) {
        this.http = http;
        this.restaurantsUrl = 'api/restaurants'; // URL to web api
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
    }
    CartService.prototype.getItems = function () {
        return this.http.get(this.restaurantsUrl)
            .toPromise()
            .then(function (response) { return response.json().data; })
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
        var url = this.restaurantsUrl + "/" + name;
        return this.http.delete(url, { headers: this.headers })
            .toPromise()
            .then(function () { return null; })
            .catch(this.handleError);
    };
    CartService.prototype.addItem = function (selectedItem) {
        var url = this.restaurantsUrl + "/" + name;
        return this.http.post(url, { body: selectedItem })
            .toPromise()
            .then(function () { return null; })
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