package ru.elimental.lunchvote.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.util.JSONUtil;
import ru.elimental.lunchvote.util.TestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.elimental.lunchvote.util.TestUtil.assertMatch;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class RestaurantConrtollerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void addRestaurant() throws Exception {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("New Super Restaurant");

        ResultActions action = mvc.perform(post(RestaurantConrtoller.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated())
                .andDo(print());

        Restaurant returned = JSONUtil.readFromJSON(TestUtil.getContent(action.andReturn()), Restaurant.class);
        newRestaurant.setId(returned.getId());

        assertMatch(returned, newRestaurant);
    }

    @Test
    public void getAllRestaurants() {
    }

    @Test
    public void getRestaurant() {
    }

    @Test
    public void deleteRestaurant() {
    }

    @Test
    public void deleteNotFoundRestaurant() {
    }

    @Test
    public void deleteMenu() {
    }

    @Test
    public void addDishToMenu() {
    }

    @Test
    public void addFullMenu() {
    }
}