package com.hutech.travelmanagement.repository;

import com.hutech.travelmanagement.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    void deleteById(Long id);
    Optional<News> findById(Long id);
}
