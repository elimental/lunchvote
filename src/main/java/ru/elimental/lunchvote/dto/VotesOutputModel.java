package ru.elimental.lunchvote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VotesOutputModel {

        private Long restuarantId;
        private String resutarantName;
        private Integer votes;
}
