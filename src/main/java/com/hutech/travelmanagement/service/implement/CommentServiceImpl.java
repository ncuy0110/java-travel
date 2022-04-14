package com.hutech.travelmanagement.service.implement;

import com.hutech.travelmanagement.helper.UsernameHelper;
import com.hutech.travelmanagement.model.Comment;
import com.hutech.travelmanagement.model.News;
import com.hutech.travelmanagement.model.User;
import com.hutech.travelmanagement.repository.CommentRepository;
import com.hutech.travelmanagement.repository.NewsRepository;
import com.hutech.travelmanagement.repository.UserRepository;
import com.hutech.travelmanagement.service.interfaces.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;
    private final UsernameHelper usernameHelper;
    private final UserRepository userRepository;

    public CommentServiceImpl(NewsRepository newsRepository, CommentRepository commentRepository, UsernameHelper usernameHelper, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
        this.usernameHelper = usernameHelper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createComment(String content, Long newsId) {
        User user = userRepository.findByUsername(usernameHelper.getCurrentUsername());
        News news = newsRepository.findById(newsId).orElse(null);
        try{
            commentRepository.save(new Comment(content, news, user));
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
