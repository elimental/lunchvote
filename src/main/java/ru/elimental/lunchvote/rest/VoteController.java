package ru.elimental.lunchvote.rest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.elimental.lunchvote.security.UserDetailsImpl;

@RestController
@RequestMapping(value = VoteController.REST_URL)
public class VoteController {

    final static String REST_URL = "/v1/votes";

    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createVote(@PathVariable Long id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("dsfsdfsdf");
    }

}
