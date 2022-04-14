package com.hutech.travelmanagement.web.controller.user.news;

import com.hutech.travelmanagement.helper.UsernameHelper;
import com.hutech.travelmanagement.service.implement.CommentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {
    private final CommentServiceImpl commentService;
    private final UsernameHelper usernameHelper;

    public CommentController(CommentServiceImpl commentService, UsernameHelper usernameHelper) {
        this.commentService = commentService;
        this.usernameHelper = usernameHelper;
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity postComment(@RequestBody Map<String, String> body, @PathVariable("id") Long id){
        String content = body.get("content");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", usernameHelper.getCurrentUsername());
        if(!commentService.createComment(content, id)) return ResponseEntity.badRequest().body(hashMap);
        return ResponseEntity.ok(hashMap);
    }
}
