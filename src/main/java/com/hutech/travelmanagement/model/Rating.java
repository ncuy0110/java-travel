package com.hutech.travelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @EmbeddedId
    private RatingKey id;

    @Column
    private int rate;

    @ManyToOne
    @JoinColumn(name = "news_id", insertable = false, updatable = false)
    private News news;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
