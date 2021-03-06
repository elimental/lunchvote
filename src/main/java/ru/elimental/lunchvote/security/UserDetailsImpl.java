package ru.elimental.lunchvote.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import ru.elimental.lunchvote.model.User;

import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private List<Role> authorities;
    private String password;
    private String username;
    private Long id;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    static UserDetails makeUserDetails(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setAuthorities(user.getRoles());
        userDetails.setUsername(user.getLogin());
        userDetails.setPassword(user.getPassword());
        userDetails.setId(user.getId());
        return userDetails;
    }
}
