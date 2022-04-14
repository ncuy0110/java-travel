package com.hutech.travelmanagement.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private int adultQuantity;
    private int childrenQuantity;
}
