package demo.rest;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuServiceRestController {
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "2";
    private static final String DEFAULT_FIELD = "heartRate";
}
