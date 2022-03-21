package com.example.postservice.service;

import com.example.postservice.model.Post;
import com.example.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements  PostService {

    @Autowired
    PostRepo postRepo;
    @Override
    public Post createPost(Post post) {
        return postRepo.save(post) ;
    }
}
