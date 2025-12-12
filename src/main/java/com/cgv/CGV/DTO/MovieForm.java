package com.cgv.CGV.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieForm {

    private String title;
    private String description;
    private LocalDate releaseDate;
    private Integer runningTime;
    private String ageRating;
    private String distributor;

    // 장르
    private List<String> genres = new ArrayList<>();

    // 감독
    private List<String> directors = new ArrayList<>();
}
