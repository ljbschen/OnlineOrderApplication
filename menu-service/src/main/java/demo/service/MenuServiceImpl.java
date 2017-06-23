package demo.service;

import demo.domain.MenuItem;
import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public MenuServiceImpl (RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void deleteAll() {
        this.restaurantRepository.deleteAll();
    }

    @Override
    public Page<MenuItem> findByItemPriceBetween(double low, double high, Pageable pageable) {
        return null;
    }

    @Override
    public void addRestaurants(List<Restaurant> restaurants) {
        this.restaurantRepository.save(restaurants);
    }

    @Override
    public void addMenuItem(String restaurantName, List<MenuItem> menu) {
        Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName);
        restaurant.getMenu().addAll(menu);
        restaurantRepository.save(restaurant);
    }

    @Override
    public Page<Restaurant> findRestaurantWithIn(String page, String size, String distance, Point point) {
        Pageable pageable = new PageRequest(Integer.parseInt(page), Integer.parseInt(size));
        Circle c = new Circle(point.getX(), point.getY(), Double.parseDouble(distance));
        return this.restaurantRepository.findByLocationWithin(c, pageable);
    }

    @Override
    public Restaurant findByRestaurantName(String restaurantName) {
        return this.restaurantRepository.findByRestaurantName(restaurantName);
    }
}
