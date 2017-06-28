package demo.service;

import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

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

    // blocking process to get authorization from card issuer
    @Override
    public String createPayment(Payment payment) {
        String paymentId = null;
        try {
            payment.setPaymentStatus(PaymentStatus.CREATED);
            // simulate the authorization process
            Thread.sleep(2000);
            int i = new Random().nextInt(10);
            if (i > 0) {
                this.paymentRepository.save(payment);
                paymentId = payment.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentId;
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
