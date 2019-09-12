package ru.elimental.lunchvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elimental.lunchvote.dto.DishInputModel;
import ru.elimental.lunchvote.exception.NotFoundException;
import ru.elimental.lunchvote.model.Dish;
import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.repository.RestaurantRepository;

import java.time.LocalDate;
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

    public void addMenu(Long id, DishInputModel[] menu) {
        Restaurant restaurant = getRestaurant(id);
        List<Dish> restaurantMenu = restaurant.getMenu();
        for (DishInputModel dishInputModel : menu) {
            restaurantMenu.add(makeDishFromInputModel(dishInputModel, restaurant));
        }
    }

    private Dish makeDishFromInputModel(DishInputModel inputModel, Restaurant restaurant) {
        Dish newDish = new Dish();
        newDish.setDate(LocalDate.now());
        newDish.setName(inputModel.getName());
        newDish.setRestaurant(restaurant);
        newDish.setPrice(inputModel.getPrice());
        return newDish;
    }
}
