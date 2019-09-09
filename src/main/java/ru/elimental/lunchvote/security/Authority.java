package ru.elimental.lunchvote.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import ru.elimental.lunchvote.model.Role;

@AllArgsConstructor
public class Authority implements GrantedAuthority {

    private Role authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
