package com.hutech.travelmanagement.web.controller.user.news;

import com.hutech.travelmanagement.service.implement.RatingServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RatingController {
    private final RatingServiceImpl ratingService;

    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/rate/{newsId}/{rating}")
    public String rateNews(@PathVariable Long newsId, @PathVariable int rating){
        ratingService.rateNews(newsId, rating);
        return "redirect:/news/details/" + newsId;
    }
}
