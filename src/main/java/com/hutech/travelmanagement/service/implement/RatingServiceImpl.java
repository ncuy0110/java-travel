package com.hutech.travelmanagement.service.implement;

import com.hutech.travelmanagement.helper.UsernameHelper;
import com.hutech.travelmanagement.model.News;
import com.hutech.travelmanagement.model.Rating;
import com.hutech.travelmanagement.model.RatingKey;
import com.hutech.travelmanagement.model.User;
import com.hutech.travelmanagement.repository.NewsRepository;
import com.hutech.travelmanagement.repository.RatingRepository;
import com.hutech.travelmanagement.repository.UserRepository;
import com.hutech.travelmanagement.service.interfaces.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final RatingRepository ratingRepository;
    private final UsernameHelper usernameHelper;

    public RatingServiceImpl(UserRepository userRepository, NewsRepository newsRepository, RatingRepository ratingRepository, UsernameHelper usernameHelper) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.ratingRepository = ratingRepository;
        this.usernameHelper = usernameHelper;
    }

    @Override
    public void rateNews(Long newsId, int ratingNumber) {
        User user = userRepository.findByUsername(usernameHelper.getCurrentUsername());
        News news = newsRepository.findById(newsId).orElse(null);
        if(news==null) return;
        RatingKey ratingKey = new RatingKey(user.getId(), news.getId());
        Rating rate = new Rating(ratingKey, ratingNumber, news, user);
        ratingRepository.save(rate);
    }
}
