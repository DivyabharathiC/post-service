package com.example.postservice.service;

import com.example.postservice.model.Post;
import com.example.postservice.model.PutRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post createPost(Post post);

    Post getPost(String postId);

    List<Post> getPosts();

    Post updatePosts(String postId, PutRequest putRequest);

}
