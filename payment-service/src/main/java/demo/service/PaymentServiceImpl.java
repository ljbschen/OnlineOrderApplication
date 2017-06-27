package demo.service;

import demo.domain.Payment;
import demo.domain.PaymentEvent;
import demo.domain.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final static String ORDER_EVENT_URL = "https://localhost:8080/order-service/order/events";
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public boolean createPayment(Payment payment) {
        boolean result = false;
        try {
            this.paymentRepository.save(payment);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addPaymentEvent(PaymentEvent paymentEvent) {
        boolean result = false;
        try {
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
