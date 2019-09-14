package ru.elimental.lunchvote.util;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.elimental.lunchvote.dto.VotesOutputModel;
import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.model.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {

    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    public static void assertMatch2(List<VotesOutputModel> actual, List<VotesOutputModel> excepted) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(excepted);
    }

    public static void assertMatch(Restaurant actual, Restaurant excepted) {
        assertThat(actual).isEqualToIgnoringGivenFields(excepted, "menu", "votes");
    }

    public static void assertMatch(List<Restaurant> actual, List<Restaurant> excepted) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu", "votes").isEqualTo(excepted);
    }

    public static void assertMatch(User actual, User excepted) {
        assertThat(actual).isEqualToComparingOnlyGivenFields(excepted, "login");
    }

    public static RequestPostProcessor userHttpBasic(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getLogin(), user.getPassword());
    }

    public static <T> T readFromJsonMvcResult(MvcResult result, Class<T> clazz) throws IOException {
        return JSONUtil.readFromJSON(getContent(result), clazz);
    }

    public static <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz) throws IOException {
        return JSONUtil.readListFromJSON(getContent(result), clazz);
    }
}
