package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCart {
    private String cardNumber;
    private String cardExp;
    private String cardCode;
    private String cardName;
}
