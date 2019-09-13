package ru.elimental.lunchvote.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class DishInputModel {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;
}
