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

    private String userName;
    private String orderAddress;

    public double getTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getItemPrice();
        }
        return total;
    }
}
