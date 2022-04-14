package com.hutech.travelmanagement.web.controller.admin.news;

import com.hutech.travelmanagement.helper.ModelAndViewHelper;
import com.hutech.travelmanagement.service.implement.NewsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/news")
public class NewsController {
    private final ModelAndViewHelper modelAndViewHelper;
    private NewsServiceImpl newsService;

    public NewsController(ModelAndViewHelper modelAndViewHelper, NewsServiceImpl newsService) {
        this.modelAndViewHelper = modelAndViewHelper;
        this.newsService = newsService;
    }

    @GetMapping
    public ModelAndView showNewsPage() {
        ModelAndView modelAndView = modelAndViewHelper.getMAV("/admin/layouts/news/index");
        modelAndView.addObject("news_list", newsService.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteNews(@PathVariable Long id) {
        if (newsService.deleteNews(id))
            return "redirect:/admin/news?delete_success";
        return "redirect:/admin/news?delete_failed";
    }
}
