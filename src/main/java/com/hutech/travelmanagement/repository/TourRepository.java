package com.hutech.travelmanagement.repository;

import com.hutech.travelmanagement.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    void deleteById(Long id);
    Optional<Tour> findById(Long id);
    List<Tour> findAllByDepartureDayLessThanEqualAndDepartureDayGreaterThanEqual(Date endDate, Date startDate);
}
