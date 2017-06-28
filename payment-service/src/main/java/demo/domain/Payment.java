package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Payment {
    @Id
    private String id;

    private String userId;
    private CreditCart creditCart;

    private String orderId;
    private PaymentStatus paymentStatus;

    public void process(PaymentEvent paymentEvent) {
        paymentStatus = paymentStatus.nextStatus(paymentEvent);
    }
}
