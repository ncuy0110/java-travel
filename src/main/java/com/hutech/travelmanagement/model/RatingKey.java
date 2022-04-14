package com.hutech.travelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RatingKey implements Serializable {
    private static final long serialVersionUID = 6012689419014981286L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "news_id")
    private Long newsId;
}
