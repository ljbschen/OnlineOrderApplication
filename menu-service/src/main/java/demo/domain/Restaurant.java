package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
public class Restaurant {
    @Id
    @JsonIgnore
    private String id;

    @GeoSpatialIndexed
    @Indexed(unique = true)
    private final Point location;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantCity;
    private String restaurantCuisine;

    private List<MenuItem> menu;

    @JsonCreator
    public Restaurant(@JsonProperty("latitude") double latitude, @JsonProperty("longitude") double longitude) {
        this.location = new Point(longitude, latitude);
    }
}
