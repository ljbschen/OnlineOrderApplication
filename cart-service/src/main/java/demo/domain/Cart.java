package demo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private String userId;
}
