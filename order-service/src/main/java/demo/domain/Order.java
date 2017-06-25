package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document
public class Order {
    private String userId;

    @Id
    private String orderId;
    private Date date;

    private List<Item> items;

    private String shippingAddress;

    private enum OrderStatus {
        PURCHASED, PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }

    public Order(String userId, Date date, List<Item> items, String shippingAddress) {
        this.userId = userId;
        this.date = date;
        this.items = items;
        this.shippingAddress = shippingAddress;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getItemPrice();
            total += item.getItemTax();
        }
        return total;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void process(OrderEvent orderEvent) {
        if (orderEvent.getType() == OrderEventType.CREATED) {

        }
    }
}
