package com.hutech.travelmanagement.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDto {
    private String title;
    private String departureDay;
    private String departurePoint;
    private Long adultPrice;
    private Long childrenPrice;
    private String description;
    private int total;
    private int sold;
}
