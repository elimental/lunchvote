package ru.elimental.lunchvote.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant extends BaseEntity {

    @Column(name = "name")
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Dish> menu;
}
