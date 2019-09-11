package ru.elimental.lunchvote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elimental.lunchvote.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
