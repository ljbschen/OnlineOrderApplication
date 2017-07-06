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
var cart_service_1 = require("./cart.service");
var router_1 = require("@angular/router");
var PaymentComponent = (function () {
    // injection
    function PaymentComponent(cartService, router) {
        this.cartService = cartService;
        this.router = router;
    }
    PaymentComponent.prototype.ngOnInit = function () {
        this.getItems();
    };
    PaymentComponent.prototype.onSelect = function (item) {
        this.selectedItem = item;
    };
    PaymentComponent.prototype.getItems = function () {
        var _this = this;
        this.cartService.getItems()
            .then(function (items) {
            _this.items = items;
        });
    };
    PaymentComponent.prototype.delete = function (item) {
        var _this = this;
        this.cartService.delete(item.itemName)
            .then(function () {
            _this.items = _this.items.filter(function (h) { return h !== item; });
            if (_this.selectedItem === item) {
                _this.selectedItem = null;
            }
        });
    };
    PaymentComponent.prototype.checkOut = function () {
        var _this = this;
        this.cartService.checkOut()
            .then(function (result) {
            console.info(result);
            if (result.status == 202) {
                alert("order placed successfully");
                _this.router.navigate(result.body);
            }
            else {
                alert("order declined");
                _this.router.navigate(['/restaurants']);
            }
        });
    };
    return PaymentComponent;
}());
PaymentComponent = __decorate([
    core_1.Component({
        selector: 'my-payment',
        templateUrl: './payment.component.html',
        styleUrls: ['./payment.component.css']
    }),
    __metadata("design:paramtypes", [cart_service_1.CartService, router_1.Router])
], PaymentComponent);
exports.PaymentComponent = PaymentComponent;
//# sourceMappingURL=payment.component.js.map