package com.hutech.travelmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hutech.travelmanagement.web.dto.TourDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "tour")
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date departureDay;

    @Column
    private String departurePoint;

    @Column
    private Long adultPrice;

    @Column
    private Long childrenPrice;

    @Column
    private String description;

    @Column
    private int total;

    @Column
    private int sold;

    @Column
    private String image;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<BookedTour> bookedTours;

    public boolean compareByName(String name) {
        return this.title.toUpperCase().contains(name.toUpperCase());
    }

    public Tour(TourDto tourDto, String image) throws ParseException {
        this.title = tourDto.getTitle();
        this.departureDay = new SimpleDateFormat("dd/MM/yyyy").parse(tourDto.getDepartureDay());
        this.departurePoint = tourDto.getDeparturePoint();
        this.adultPrice = tourDto.getAdultPrice();
        this.childrenPrice = tourDto.getChildrenPrice();
        this.description = tourDto.getDescription();
        this.total = tourDto.getTotal();
        this.sold = tourDto.getSold();
        this.image = image;
    }
}
