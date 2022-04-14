package com.hutech.travelmanagement.web.controller.admin.news;

import com.hutech.travelmanagement.service.implement.NewsServiceImpl;
import com.hutech.travelmanagement.web.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/news/edit/{id}")
public class EditNewsController {
    private final NewsServiceImpl newsService;

    @ModelAttribute("news")
    public NewsDto newsDto(){
        return new NewsDto();
    }

    public EditNewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ModelAndView showEditNewsPage(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/layouts/news/edit_news");
        modelAndView.addObject("news", newsService.findById(id));
        return modelAndView;
    }

    @PostMapping
    public String editNews(@PathVariable Long id, @ModelAttribute("news")NewsDto newsDto, MultipartFile file){
        if(newsService.editNews(id, newsDto, file))
            return "redirect:/admin/news?edit_success";
        return "redirect:/admin/news?edit_failed";
    }
}
