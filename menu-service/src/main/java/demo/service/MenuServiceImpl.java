package demo.service;

import demo.domain.MenuItem;
import demo.domain.Request;
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
    public boolean deleteAll(Request request) {
        if (request.isAdmin()) {
            this.restaurantRepository.deleteAll();
            return true;
        } else return false;
    }

    @Override
    public Page<MenuItem> findByItemPriceBetween(double low, double high, Pageable pageable) {
        return null;
    }

    @Override
    public boolean addRestaurants(Request request) {
        if (request.isAdmin()) {
            this.restaurantRepository.save(request.getRestaurants());
            return true;
        } else return false;
    }

    @Override
    public boolean addMenuItem(String restaurantName, Request request) {
        if (request.isAdmin()) {
            List<MenuItem> menu = request.getItems();
            Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName);
            restaurant.getMenu().addAll(menu);
            restaurantRepository.save(restaurant);
            return true;
        } else return false;
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
