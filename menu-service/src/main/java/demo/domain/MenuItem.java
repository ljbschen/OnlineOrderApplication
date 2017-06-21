package demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "MENU")
public class MenuItem {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    private enum ItemType {
        APPETIZERS, ENTREES, SIDES, SPECIALS, DRINKS
    }

    private String itemName;
    private String itemDescription;
    private double itemPrice;
    private ItemType itemType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_id")
    private Restaurant restaurant;
}
