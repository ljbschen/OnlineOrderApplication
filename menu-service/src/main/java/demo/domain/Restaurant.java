package demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    private String restaurantName;
    private String restaurantAddress;
    private String restaurantCity;
    private String restaurantCuisine;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menu;
}
