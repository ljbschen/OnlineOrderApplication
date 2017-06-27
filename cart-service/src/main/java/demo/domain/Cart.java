package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Cart {
    private HashMap<CartItem, Integer> itemMap;
    private String userId;
}
