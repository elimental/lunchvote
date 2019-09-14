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
import ru.elimental.lunchvote.model.User;
import ru.elimental.lunchvote.service.UserService;
import ru.elimental.lunchvote.util.JSONUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.elimental.lunchvote.util.TestUtil.assertMatch;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Sql(scripts = "classpath:data-test.sql")
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Test
    public void register() throws Exception {
        User newUser = new User("newlogin", "newpassword");

        mvc.perform(post(UserController.REST_URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.writeValue(newUser)))
                .andExpect(status().isCreated())
                .andDo(print());

        User created = userService.getUserByLogin(newUser.getLogin());
        assertMatch(created, newUser);
    }
}