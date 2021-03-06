package demo.rest;

import demo.domain.Invoice;
import demo.domain.Payment;
import demo.domain.PaymentEvent;
import demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentServiceRestController {
    private PaymentService paymentService;

    @Autowired
    public PaymentServiceRestController (PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity<Invoice> createPayment(@RequestBody Payment payment) {
        String paymentId = this.paymentService.createPayment(payment);
        if (paymentId != null) {
            Invoice invoice = new Invoice(this.paymentService.getPaymentById(paymentId));
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>((Invoice) null, HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/payment/{paymentId}", method = RequestMethod.GET)
    public ResponseEntity<Payment> getPaymentById(@PathVariable String paymentId) {
        return new ResponseEntity<>(this.paymentService.getPaymentById(paymentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/payment/events", method = RequestMethod.POST)
    public ResponseEntity addPaymentEvent(@RequestBody PaymentEvent paymentEvent) {
        boolean result = this.paymentService.addPaymentEvent(paymentEvent);
        if (result) return new ResponseEntity(HttpStatus.CREATED);
        else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}
