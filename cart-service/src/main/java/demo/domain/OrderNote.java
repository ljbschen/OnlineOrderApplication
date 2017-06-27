package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderNote {
    private String orderNote;
    private String shippingAddress;
}
