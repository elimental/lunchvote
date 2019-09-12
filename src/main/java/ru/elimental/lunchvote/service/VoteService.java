package ru.elimental.lunchvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.elimental.lunchvote.exception.DateTimeException;
import ru.elimental.lunchvote.exception.NotFoundException;
import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.model.Vote;
import ru.elimental.lunchvote.repository.RestaurantRepository;
import ru.elimental.lunchvote.repository.UserRepository;
import ru.elimental.lunchvote.repository.VoteRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class VoteService {

    private static final LocalTime THRESHOLD_TIME = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void createVote(Long restaurantId, Long userId) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (currentDateTime.toLocalTime().isAfter(THRESHOLD_TIME)) {
            throw new DateTimeException(String.format("It's too late. You have to vote by %s", THRESHOLD_TIME));
        }
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        if (restaurant == null) {
            throw new NotFoundException(String.format("Restaurant with id=%s was not found ", restaurantId));
        }
        Vote vote = voteRepository.findByDate(currentDateTime.toLocalDate()).orElse(new Vote());
        vote.setDate(currentDateTime.toLocalDate());
        vote.setRestaurant(restaurant);
        vote.setUser(userRepository.getOne(userId));
        voteRepository.save(vote);
    }
}
