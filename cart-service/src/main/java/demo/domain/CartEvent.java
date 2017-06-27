package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document
public class CartEvent {
    @Id
    private String eventId;

    private String userId;

    private CartEventType cartEventType;

    private CartItem item;

    private Date date;
}
