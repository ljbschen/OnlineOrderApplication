package demo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class CartEvent {
    private String userId;

    private CartEventType cartEventType;

    private CartItem item;

    private Date date;
}
