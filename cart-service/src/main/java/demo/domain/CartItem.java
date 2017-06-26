package demo.domain;

import lombok.Data;

@Data
public class CartItem {
    private String itemName;
    private double itemPrice;
    private double itemTax;
    private String restaurantName;
}
