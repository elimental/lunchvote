package ru.elimental.lunchvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elimental.lunchvote.exception.NotFoundException;
import ru.elimental.lunchvote.model.Dish;
import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.repository.RestaurantRepository;

import java.util.List;

@Service
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(Long id) throws NotFoundException {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(
                "Restuarant with id=%s was not found", id)));
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.delete(getRestaurant(id));
    }

    public void deleteMenu(Long id) {
        getRestaurant(id).getMenu().clear();
    }

    public void addDishToMenu(Long id, Dish dish) {
        getRestaurant(id).getMenu().add(dish);
    }

    public void addFullMenu(Long id, List<Dish> dishes) {
        getRestaurant(id).getMenu().addAll(dishes);
    }
}
