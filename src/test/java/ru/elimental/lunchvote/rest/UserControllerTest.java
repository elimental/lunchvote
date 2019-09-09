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
import ru.elimental.lunchvote.model.User;
import ru.elimental.lunchvote.util.JSONUtil;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void register() throws Exception {
        User newUser = new User("dddddddd", "gffffffff");

        mvc.perform(post(UserController.REST_URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.writeValue(newUser)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}