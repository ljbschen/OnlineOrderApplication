package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Invoice {
    @Id
    private String id;
    private String paymentId;
    private String orderId;
    private Data deliverDate;

    public Invoice(Payment payment) {
        this.paymentId = payment.getId();
        this.orderId = payment.getOrderId();
    }
}
