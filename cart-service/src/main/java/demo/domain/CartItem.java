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

    private int itemQuantity;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CartItem)) {
            return false;
        }

        CartItem cartItem = (CartItem) o;
        return cartItem.getItemName().equals(this.getItemName()) &&
                cartItem.getItemRestaurantName().equals(this.getItemRestaurantName());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + itemName.hashCode();
        result = 31 * result + itemRestaurantName.hashCode();
        return result;
    }
}
