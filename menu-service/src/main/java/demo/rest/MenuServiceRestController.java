package demo.rest;

import demo.domain.Request;
import demo.domain.Restaurant;
import demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity uploadRestaurant(@RequestBody Request request) {
        if (this.menuService.addRestaurants(request)) return new ResponseEntity(HttpStatus.CREATED);
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/menu/addItems", method = RequestMethod.POST)
    public ResponseEntity addMenuItemsWithRestaurantName(@RequestBody Request request, @RequestParam(name = "name") String restaurantName) {
        if (this.menuService.addMenuItem(restaurantName, request)) return new ResponseEntity(HttpStatus.CREATED);
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/menu/purge", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll(@RequestBody Request request) {
        if (this.menuService.deleteAll(request)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/menu/near", method = RequestMethod.GET)
    public Page<Restaurant> findRestaurantWithIn(
            @RequestParam(name = "lat") String lat,
            @RequestParam(name = "lng") String lng,
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) String page,
            @RequestParam(name = "size", defaultValue = DEFAULT_SIZE) String size,
            @RequestParam(name = "distance", defaultValue = DEFAULT_DISTANCE) String distance) {
        return this.menuService.findRestaurantWithIn(page, size, distance, new Point(Double.parseDouble(lng), Double.parseDouble(lat)));
    }

    @RequestMapping(value = "/menu/{restaurantName}", method = RequestMethod.GET)
    public Restaurant findByRestaurantName(@PathVariable("restaurantName") String restaurantName) {
        return this.menuService.findByRestaurantName(restaurantName);
    }
}
