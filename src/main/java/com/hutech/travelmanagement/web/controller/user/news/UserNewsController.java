package com.hutech.travelmanagement.web.controller.user.news;

import com.hutech.travelmanagement.helper.ModelAndViewHelper;
import com.hutech.travelmanagement.model.News;
import com.hutech.travelmanagement.model.Rating;
import com.hutech.travelmanagement.service.implement.NewsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserNewsController {
    private final NewsServiceImpl newsService;
    private final ModelAndViewHelper modelAndViewHelper;

    public UserNewsController(NewsServiceImpl newsService, ModelAndViewHelper modelAndViewHelper) {
        this.newsService = newsService;
        this.modelAndViewHelper = modelAndViewHelper;
    }

    @GetMapping("/news")
    public ModelAndView showNewsPage(){
        ModelAndView modelAndView = modelAndViewHelper.getMAV("/public/news/index");
        modelAndView.addObject("news_list", newsService.findAll());
        return modelAndView;
    }

    @GetMapping("/news/details/{id}")
    public ModelAndView showNewsDetailsPage(@PathVariable Long id){
        ModelAndView modelAndView = modelAndViewHelper.getMAV("/public/news/news_details");
        float rate = 0;
        News news = newsService.findById(id);
        for(Rating rating : news.getRatings()){
            rate += (float) rating.getRate();
        }
        rate/=news.getRatings().size();
        modelAndView.addObject("news", news);
        modelAndView.addObject("rate", Math.round(rate * 10) / 10.0);
        return modelAndView;
    }
}
