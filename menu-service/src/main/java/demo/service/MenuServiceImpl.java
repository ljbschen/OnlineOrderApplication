package demo.service;

import demo.domain.MenuItem;
import demo.domain.MenuItemRepository;
import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuItemRepository menuItemRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public MenuServiceImpl (MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void deleteByItemName(String itemName) {
        menuItemRepository.deleteByItemName(itemName);
    }

    @Override
    public MenuItem findByItemName(String itemName) {
        return menuItemRepository.findByItemName(itemName);
    }

    @Override
    public Page<MenuItem> findByItemType(String itemType, Pageable pageable) {
        return null;
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
        restaurantRepository.saveAndFlush(restaurant);
    }

    @Override
    public Page<Restaurant> findAll(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return this.restaurantRepository.findAll(pageable);
    }
}
