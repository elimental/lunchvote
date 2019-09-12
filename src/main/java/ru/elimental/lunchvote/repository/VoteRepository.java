package ru.elimental.lunchvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elimental.lunchvote.model.User;
import ru.elimental.lunchvote.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByDateAndUser(LocalDate date, User user);

    List<Vote> findAllByDate(LocalDate localDate);

}
