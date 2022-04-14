package com.hutech.travelmanagement.web.controller.admin.news;

import com.hutech.travelmanagement.service.implement.NewsServiceImpl;
import com.hutech.travelmanagement.web.dto.NewsDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/news/create")
public class AdditionNewsController {
    private final NewsServiceImpl newsService;

    public AdditionNewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    @ModelAttribute("news")
    public NewsDto newsDto() {
        return new NewsDto();
    }

    @GetMapping
    public String showAdditionNewsPage() {
        return "admin/layouts/news/add_news";
    }

    @PostMapping
    public String addNews(@RequestParam("file") MultipartFile file, @ModelAttribute("news") NewsDto newsDto) {
        if (newsService.createNews(file, newsDto))
            return "redirect:/admin/news?create_success";
        return "redirect:/admin/news?create_failed";
    }
}
