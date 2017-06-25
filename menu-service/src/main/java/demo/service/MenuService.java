package demo.service;

import demo.domain.MenuItem;
import demo.domain.Request;
import demo.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.Point;

import java.util.List;

public interface MenuService {

    boolean deleteAll(Request request);

    List<MenuItem> findByItemPriceBetween(String restaurantName, String low, String high);

    boolean addRestaurants (Request request);

    boolean addMenuItem(String restaurantName, Request request);

    Page<Restaurant> findRestaurantWithIn(String page, String size, String distance, Point point);

    Restaurant findByRestaurantName(String restaurantName);

    boolean deleteRestaurantByRestaurantName(Request request, String name);
}
