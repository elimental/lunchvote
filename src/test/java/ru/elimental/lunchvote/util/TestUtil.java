package ru.elimental.lunchvote.util;

import org.springframework.test.web.servlet.MvcResult;
import ru.elimental.lunchvote.model.Restaurant;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {

    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    public static void assertMatch(Restaurant actual, Restaurant excepted) {
        assertThat(actual).isEqualToIgnoringGivenFields(excepted, "menu");
    }
}
