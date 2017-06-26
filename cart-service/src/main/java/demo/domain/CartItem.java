package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CartItem {
    private String itemName;
    private double itemPrice;
    private double itemTax;
    private String itemRestaurantName;
    private String itemNote;
    private int itemQuantity;
}
