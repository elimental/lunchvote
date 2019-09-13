package ru.elimental.lunchvote.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.elimental.lunchvote.security.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "login")
    @Size(min = 5, max = 20)
    private String login;

    @Column(name = "password")
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vote> votes;

    public User(String login, String password) {
        this(null, login, password, null);
    }

    public User(Long id, String login, String password, List<Role> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.roles = roles;
    }
}
