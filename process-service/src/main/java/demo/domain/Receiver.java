package demo.domain;


import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;

@Component
public class Receiver {
    public void receiveMessage(byte[] message) {
        try {
            System.out.println("Received <" + new String(message, "UTF-8") + ">");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
