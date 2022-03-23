package com.example.postservice.service;

import com.example.postservice.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    Post createPost(Post post);

    Post getPost(String postId);

}
