package demo.service;

import demo.domain.Payment;
import demo.domain.PaymentEvent;

public interface PaymentService {
    Payment getPaymentById(String id);

    String createPayment(Payment payment);

    boolean addPaymentEvent(PaymentEvent paymentEvent);
}
