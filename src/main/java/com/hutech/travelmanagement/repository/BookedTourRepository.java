package com.hutech.travelmanagement.repository;

import com.hutech.travelmanagement.model.BookedTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface BookedTourRepository extends JpaRepository<BookedTour, Long> {
    List<BookedTour> findAllByCreatedAtGreaterThanEqual(LocalDateTime startDate);
}
