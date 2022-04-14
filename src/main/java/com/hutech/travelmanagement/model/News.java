package com.hutech.travelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String image;

    @OneToMany(mappedBy = "news", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Rating> ratings;

    @OneToMany(mappedBy = "news", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<Comment> comments;

    public News(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }
}
