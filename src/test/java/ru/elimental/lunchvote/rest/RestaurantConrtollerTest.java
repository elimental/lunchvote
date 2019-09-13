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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import ru.elimental.lunchvote.dto.DishInputModel;
import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.service.RestaurantService;
import ru.elimental.lunchvote.util.JSONUtil;
import ru.elimental.lunchvote.util.TestUtil;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.elimental.lunchvote.util.TestData.*;
import static ru.elimental.lunchvote.util.TestUtil.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Sql(scripts = "classpath:data-test.sql")
@Transactional
public class RestaurantConrtollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void addRestaurant() throws Exception {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("New Super Restaurant");

        ResultActions action = mockMvc.perform(post(RestaurantConrtoller.REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated())
                .andDo(print());

        Restaurant returned = JSONUtil.readFromJSON(TestUtil.getContent(action.andReturn()), Restaurant.class);
        newRestaurant.setId(returned.getId());

        assertMatch(returned, newRestaurant);
    }

    @Test
    public void addRestaurantByUser() throws Exception {
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName("New Super Restaurant");

        ResultActions action = mockMvc.perform(post(RestaurantConrtoller.REST_URL)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.writeValue(newRestaurant)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    public void accessByNotRegisteredUser() throws Exception {
        mockMvc.perform(get(RestaurantConrtoller.REST_URL)
                .with(userHttpBasic(NOT_REGISTERED_USER)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void getAllRestaurants() throws Exception {
        mockMvc.perform(get(RestaurantConrtoller.REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Restaurant.class),
                        List.of(MCDONALDS, KFC, BURGER_KING)));
    }

    @Test
    public void getRestaurant() throws Exception {
        mockMvc.perform(get(RestaurantConrtoller.REST_URL + "/3")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), MCDONALDS));
    }

    @Test
    public void addDishToMenu() throws Exception {
        DishInputModel inputModel = new DishInputModel();
        inputModel.setName("havchik");
        inputModel.setPrice(new BigDecimal(10000000.35));

        mockMvc.perform(post(RestaurantConrtoller.REST_URL + "/4/menu")
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.writeValue(inputModel)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void addDishToMenuByUser() throws Exception {
        DishInputModel inputModel = new DishInputModel();
        inputModel.setName("havchik");
        inputModel.setPrice(new BigDecimal(10000000.35));

        mockMvc.perform(post(RestaurantConrtoller.REST_URL + "/4/menu")
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.writeValue(inputModel)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }
}