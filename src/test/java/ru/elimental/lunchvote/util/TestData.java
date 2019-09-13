package ru.elimental.lunchvote.util;

import ru.elimental.lunchvote.model.Restaurant;
import ru.elimental.lunchvote.model.User;
import ru.elimental.lunchvote.security.Role;

import java.util.List;

public class TestData {

    public static final User USER = new User(1L, "userlogin", "userpassword", List.of(Role.USER));
    public static final User ADMIN = new User(2L, "admin", "admin", List.of(Role.USER, Role.ADMIN));
    public static final User NOT_REGISTERED_USER = new User(3L, "unknown", "unknown", List.of(Role.USER));

    public static final Restaurant MCDONALDS = new Restaurant(3L, "mcdonalds");
    public static final Restaurant KFC = new Restaurant(4L, "kfc");
    public static final Restaurant BURGER_KING = new Restaurant(5L, "burger king");
}