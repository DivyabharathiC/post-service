package com.example.postservice.service;

import com.example.postservice.model.Post;
import com.example.postservice.model.PutRequest;
import com.example.postservice.model.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post createPost(Post post);

    Post getPost(String postId);

    List<Response> getPosts();

    Post updatePosts(String postId, PutRequest putRequest);

    String deletePost(String postId);


}
