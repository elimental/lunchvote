package ru.elimental.lunchvote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.elimental.lunchvote.model.Dish;
import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantConrtoller.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantConrtoller {

    public static final String REST_URL = "/v1/restaurants";

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantConrtoller(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        return restaurantService.getRestaurant(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }

    @DeleteMapping("/{id}/menu")
    public void deleteMenu(@PathVariable Long id) {
        restaurantService.deleteMenu(id);
    }

    @PostMapping(value = "/{id}/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addDishToMenu(@PathVariable Long id, @Valid @RequestBody Dish dish) {
        restaurantService.addDishToMenu(id, dish);
    }

    @PostMapping(value = "/{id}/fullmenu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addFullMenu(@PathVariable Long id, @Valid @RequestBody List<Dish> dishes) {
        restaurantService.addFullMenu(id, dishes);
    }
}
