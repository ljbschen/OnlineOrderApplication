package demo;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class EdgeServiceApplication {
    private final static Logger LOGGER = Logger.getLogger(EdgeServiceApplication.class);
    public static void main (String[] args) {
        LOGGER.info("Edge-Service starting...");
        SpringApplication.run(EdgeServiceApplication.class);
    }
}
