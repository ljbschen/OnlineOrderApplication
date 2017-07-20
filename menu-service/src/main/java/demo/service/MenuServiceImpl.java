package demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.domain.MenuItem;
import demo.domain.Request;
import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private RestaurantRepository restaurantRepository;
    private final static Logger LOGGER = Logger.getLogger(MenuServiceImpl.class);

    @Autowired
    public MenuServiceImpl (RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @HystrixCommand(fallbackMethod = "processDeleteAllFallback")
    @Override
    public boolean deleteAll(Request request) {
        if (request.isAdmin()) {
            this.restaurantRepository.deleteAll();
            return true;
        } else return false;
    }

    public void processDeleteAllFallback(Throwable e) {
        LOGGER.info("Error while deleting all, Exception:" + e.getMessage());
    }

    @Override
    public List<MenuItem> findByItemPriceBetween(String restaurantName, String low, String high) {
        double l = Double.parseDouble(low);
        double h = Double.parseDouble(high);
        Restaurant restaurant = this.restaurantRepository.findByRestaurantName(restaurantName);
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        for (MenuItem item : restaurant.getMenu()) {
            if (item.getItemPrice() > l && item.getItemPrice() < h) menu.add(item);
        }
        return menu;
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

    @Override
    public boolean deleteRestaurantByRestaurantName(Request request, String name) {
        if (request.isAdmin()) {
            this.restaurantRepository.deleteRestaurantByRestaurantName(name);
            return true;
        } else return false;
    }

    @Override
    public Page<Restaurant> findAll(String page, String size) {
        return this.restaurantRepository.findAll(new PageRequest(Integer.parseInt(page), Integer.parseInt(size)));
    }
}
