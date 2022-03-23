package com.example.postservice.service;

import com.example.postservice.model.Post;
import com.example.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class PostServiceImpl implements  PostService {

    @Autowired
    PostRepo postRepo;
    @Override
    public Post createPost(Post post) {
        return postRepo.save(post) ;
    }

    @Override
    public Post getPost(String postId) {
        return postRepo.findById(postId).get();
    }
    @Override
    public List<Post> getPosts() {
        return  postRepo.findAll();
    }


}
