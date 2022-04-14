package com.hutech.travelmanagement.repository;

import com.hutech.travelmanagement.model.Rating;
import com.hutech.travelmanagement.model.RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingKey> {
}
