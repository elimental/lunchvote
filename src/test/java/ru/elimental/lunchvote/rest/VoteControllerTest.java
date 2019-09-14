package ru.elimental.lunchvote.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.elimental.lunchvote.dto.VotesOutputModel;
import ru.elimental.lunchvote.service.VoteService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.elimental.lunchvote.util.TestData.MCDONALDS;
import static ru.elimental.lunchvote.util.TestData.USER;
import static ru.elimental.lunchvote.util.TestUtil.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Sql(scripts = "classpath:data-test.sql")
@Transactional
public class VoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VoteService voteService;

    @Test
    public void createVote() throws Exception {
        LocalTime localTime = LocalTime.now();
        if (localTime.isBefore(VoteService.THRESHOLD_TIME)) {
            mockMvc.perform(post(VoteController.REST_URL + "/3")
                    .with(userHttpBasic(USER)))
                    .andExpect(status().isCreated())
                    .andDo(print());
        } else {
            mockMvc.perform(post(VoteController.REST_URL + "/3")
                    .with(userHttpBasic(USER)))
                    .andExpect(status().isForbidden())
                    .andDo(print());
        }
    }

    @Test
    public void getVotes() throws Exception {
        voteService.createVote(3L, 1L);
        voteService.createVote(3L, 2L);
        List<VotesOutputModel> outputModels = new ArrayList<>();
        outputModels.add(new VotesOutputModel(3L, MCDONALDS.getName(), 2));
        mockMvc.perform(get(VoteController.REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch2(readListFromJsonMvcResult(result, VotesOutputModel.class), outputModels));
    }
}