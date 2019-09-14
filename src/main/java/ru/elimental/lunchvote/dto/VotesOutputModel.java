package ru.elimental.lunchvote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class VotesOutputModel {

    private Long restuarantId;
    private String resutarantName;
    private Integer votes;
}
