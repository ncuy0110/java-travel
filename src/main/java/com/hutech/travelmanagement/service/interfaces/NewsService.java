package com.hutech.travelmanagement.service.interfaces;

import com.hutech.travelmanagement.model.News;
import com.hutech.travelmanagement.web.dto.NewsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    List<News> findAll();
    boolean createNews(MultipartFile file, NewsDto newsDto);
    News findById(Long id);
    boolean editNews(Long id, NewsDto newsDto, MultipartFile file);
    boolean deleteNews(Long id);
}
