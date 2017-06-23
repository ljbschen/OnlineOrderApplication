package demo.rest;

import demo.domain.MenuItem;
import demo.domain.Restaurant;
import demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuServiceRestController {
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";
    private static final String DEFAULT_DISTANCE = "1";

    private final MenuService menuService;

    @Autowired
    public MenuServiceRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/menu/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadRestaurant(@RequestBody List<Restaurant> restaurants) {
        this.menuService.addRestaurants(restaurants);
    }

    @RequestMapping(value = "/menu/addItems", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMenuItemsWithRestaurantName(@RequestBody List<MenuItem> menuItems, @RequestParam(name = "restaurantName") String restaurantName) {
        this.menuService.addMenuItem(restaurantName, menuItems);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public Page<Restaurant> findRestaurantWithIn(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) String page,
            @RequestParam(name = "size", defaultValue = DEFAULT_SIZE) String size,
            @RequestParam(name = "distance", defaultValue = DEFAULT_DISTANCE) String distance,
            @RequestParam(name = "lat") String lat,
            @RequestParam(name = "lng") String lng) {
        return this.menuService.findRestaurantWithIn(page, size, distance, new Point(Double.parseDouble(lng), Double.parseDouble(lat)));
    }

    @RequestMapping(value = "/menu/{restaurantName}", method = RequestMethod.GET)
    public Restaurant findByRestaurantName(@PathVariable("restaurantName") String restaurantName) {
        return this.menuService.findByRestaurantName(restaurantName);
    }
}
