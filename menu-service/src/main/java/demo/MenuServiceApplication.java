package demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuServiceApplication {
    private final static Logger LOGGER = Logger.getLogger(MenuServiceApplication.class);

    @HystrixCommand(fallbackMethod = "processApplicationFallback")
    public static void main (String[] args) {
        SpringApplication.run(MenuServiceApplication.class);
    }

    public void processApplicationFallback(Throwable e) {
        LOGGER.info("Error while deleting all, Exception:" + e.getMessage());
    }
}