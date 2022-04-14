package com.hutech.travelmanagement.service.implement;

import com.hutech.travelmanagement.model.News;
import com.hutech.travelmanagement.repository.NewsRepository;
import com.hutech.travelmanagement.service.interfaces.NewsService;
import com.hutech.travelmanagement.service.interfaces.StorageService;
import com.hutech.travelmanagement.web.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final StorageService storageService;

    public NewsServiceImpl(NewsRepository newsRepository, StorageService storageService) {
        this.newsRepository = newsRepository;
        this.storageService = storageService;
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public boolean createNews(MultipartFile file, NewsDto newsDto) {
        try{
            String name = storageService.store(file);
            News news = new News(newsDto.getTitle(), newsDto.getDescription(), name);
            newsRepository.save(news);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public boolean editNews(Long id, NewsDto newsDto, MultipartFile file) {
        try{
            News editNews = newsRepository.findById(id).orElse(null);
            if(editNews==null) return false;
            String name = storageService.store(file);
            News news = new News(newsDto.getTitle(), newsDto.getDescription(), name);
            news.setId(id);
            newsRepository.save(news);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNews(Long id) {
        try{
            newsRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
