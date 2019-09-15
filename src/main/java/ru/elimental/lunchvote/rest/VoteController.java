package ru.elimental.lunchvote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.elimental.lunchvote.dto.VotesOutputModel;
import ru.elimental.lunchvote.security.UserDetailsImpl;
import ru.elimental.lunchvote.service.VoteService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = VoteController.REST_URL)
public class VoteController {

    final static String REST_URL = "/v1/votes";

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createVote(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        voteService.createVote(id, userDetails.getId());
    }

    @GetMapping
    public List<VotesOutputModel> getVotes() {
        return voteService.getVotes();
    }

}
