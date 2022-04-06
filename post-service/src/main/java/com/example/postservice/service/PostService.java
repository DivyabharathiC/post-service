package com.example.postservice.service;

import com.example.postservice.dto.PostDTO;
import com.example.postservice.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostDTO createPost(Post post);

    PostDTO getPost(String postId);

    

    PostDTO updatePosts(String postId, Post post);

    String deletePost(String postId);


    List<PostDTO> getPosts(Integer page, Integer size);


}
