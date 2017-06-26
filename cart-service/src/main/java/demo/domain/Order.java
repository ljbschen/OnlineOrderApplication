package demo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private List<CartItem> items;
    private String orderId;
}
