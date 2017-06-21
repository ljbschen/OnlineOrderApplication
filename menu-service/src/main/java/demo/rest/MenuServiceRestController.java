package demo.rest;

import demo.domain.MenuItem;
import demo.domain.Restaurant;
import demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuServiceRestController {
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";

    private final MenuService menuService;

    @Autowired
    public MenuServiceRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/restaurant/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadRestaurant(@RequestBody List<Restaurant> restaurants) {
        this.menuService.addRestaurants(restaurants);
    }

    @RequestMapping(value = "/restaurant/addMenuItems", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenuItemsWithRestaurantName(@RequestBody List<MenuItem> menuItems, @RequestParam(name = "restaurantName") String restaurantName) {
        this.menuService.addMenuItem(restaurantName, menuItems);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public Page<Restaurant> findAllRestaurant(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(name = "size", defaultValue = DEFAULT_SIZE) Integer size) {
        return this.menuService.findAll(page, size);
    }

    @RequestMapping(value = "/restaurant/{restaurantName}", method = RequestMethod.DELETE)
    public void deleteByItemName(@PathVariable("restaurantName") String restaurantName) {
        this.menuService.deleteByItemName(restaurantName);

    }

}
