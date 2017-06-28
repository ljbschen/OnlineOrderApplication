package demo.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final static String ORDER_EVENT_URL = "https://localhost:8080/order-service/order/events";
    private final static String QUEUE_NAME = "spring-boot";
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
                // push task to MQ for process service to complete the detail processing
                // serialize payment to string
                pushToQueue(payment.toString());
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

    private void pushToQueue(String payment) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicPublish("", QUEUE_NAME, null, payment.getBytes());

        channel.close();
        connection.close();
        System.out.println("pushed to queue for payment: " + payment);
    }
}
