package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document
public class Order {
    private String userId;

    @Id
    private String orderId;
    private Date date;
    private String orderNote;

    private List<Item> items;

    private String shippingAddress;

    private double orderTotal;
    private OrderStatus orderStatus;

    @JsonCreator
    public Order(@JsonProperty("userId") String userId, @JsonProperty("items") List<Item> items, @JsonProperty("shippingAddress") String shippingAddress,
                 @JsonProperty("orderNote") String orderNote) {
        this.date = new Date();
        this.userId = userId;
        this.items = items;
        this.shippingAddress = shippingAddress;
        this.orderNote = orderNote;
        this.orderStatus = OrderStatus.PURCHASED;
        this.orderTotal = getTotalPrice();
    }

    private double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getItemPrice();
            total += item.getItemTax();
        }
        return total;
    }
}
