package com.hutech.travelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookedTour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int adultQuantity;

    @Column
    private Long adultPrice;

    @Column
    private int childrenQuantity;

    @Column
    private Long childrenPrice;

    @Column
    private Long totalCost;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;

    public boolean compareTo(BookedTour bookedTour){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return createdAt.format(formatter).equals(bookedTour.getCreatedAt().format(formatter));
    }

    public BookedTour(int adultQuantity, int childrenQuantity, User user, Tour tour){
        this.adultQuantity = adultQuantity;
        this.adultPrice = tour.getAdultPrice();
        this.childrenQuantity = childrenQuantity;
        this.childrenPrice = tour.getChildrenPrice();
        this.totalCost = adultQuantity*tour.getAdultPrice() + childrenQuantity*tour.getChildrenPrice();
        this.user = user;
        this.tour = tour;
    }
}
