package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MenuItem {
    private enum ItemType {
        APPETIZERS, ENTREES, SIDES, SPECIALS, DRINKS
    }

    private String itemName;
    private String itemDescription;
    private double itemPrice;
    private ItemType itemType;
}
