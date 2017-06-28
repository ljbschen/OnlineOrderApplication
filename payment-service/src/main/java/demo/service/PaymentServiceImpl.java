package demo.service;

import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
            payment.setPaymentStatus(PaymentStatus.CREATED);
            this.paymentRepository.save(payment);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addPaymentEvent(PaymentEvent paymentEvent) {
        // add payment event to current payment status and push an orderEvent to order service
        boolean result = false;
        try {
            Payment payment = getPaymentById(paymentEvent.getPaymentId());
            payment.process(paymentEvent);

            OrderEvent orderEvent = new OrderEvent(payment);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity response = restTemplate.postForObject(ORDER_EVENT_URL, orderEvent, ResponseEntity.class);
            if (response.getStatusCode().equals(HttpStatus.CREATED)) {
                result = true;
                this.paymentRepository.save(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
