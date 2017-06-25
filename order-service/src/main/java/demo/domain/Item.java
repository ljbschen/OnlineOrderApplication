package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Item {
    private String itemName;
    private int itemQuantity;
    private String itemRestaurantName;
    private String itemNote;
    private double itemPrice;
}
