package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document
public class OrderEvent {
    @Id
    private String id;
    private Date date;
    private OrderEventType type;
    private String orderId;
}
