package ru.elimental.lunchvote.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.util.JSONUtil;
import ru.elimental.lunchvote.util.TestUtil;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.elimental.lunchvote.util.TestData.ADMIN;
import static ru.elimental.lunchvote.util.TestUtil.assertMatch;
import static ru.elimental.lunchvote.util.TestUtil.userHttpBasic;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Sql(scripts = "classpath:data-test.sql")
@Transactional
public class VoteControllerTest {

//    @Test
//    public void createVote() {
//        Restaurant newRestaurant = new Restaurant();
//        newRestaurant.setName("New Super Restaurant");
//
//        ResultActions action = mockMvc.perform(post(RestaurantConrtoller.REST_URL)
//                .with(userHttpBasic(ADMIN))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSONUtil.writeValue(newRestaurant)))
//                .andExpect(status().isCreated())
//                .andDo(print());
//
//        Restaurant returned = JSONUtil.readFromJSON(TestUtil.getContent(action.andReturn()), Restaurant.class);
//        newRestaurant.setId(returned.getId());
//
//        assertMatch(returned, newRestaurant);
//    }

    @Test
    public void getVotes() {
    }
}