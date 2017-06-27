package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentEvent {
    @Id
    private String id;

    private String paymentId;

    private PaymentEventType paymentEventType;
}
