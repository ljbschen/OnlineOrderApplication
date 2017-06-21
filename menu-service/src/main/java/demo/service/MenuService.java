package demo.service;

import demo.domain.MenuItem;
import demo.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {
    void deleteByItemName(String itemName);

    MenuItem findByItemName(String itemName);

    Page<MenuItem> findByItemType(String itemType, Pageable pageable);

    Page<MenuItem> findByItemPriceBetween(double low, double high, Pageable pageable);

    void addRestaurants (List<Restaurant> restaurants);

    void addMenuItem(String restaurantName, List<MenuItem> menu);

    Page<Restaurant> findAll(Integer page, Integer size);
}
