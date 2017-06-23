package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Request {
    private boolean admin;
    private List<Restaurant> restaurants;
    private List<MenuItem> items;
}
