package com.hutech.travelmanagement.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    @Column
    private String title;

    @Column
    private String description;
}
