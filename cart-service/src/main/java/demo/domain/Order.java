package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Order {
    private List<CartItem> items;
    private String userId;
    private String orderNote;
    private String shipping;

    public Order() {
        items = new ArrayList<CartItem>();
    }
}
