package com.example.postservice.service;

import com.example.postservice.model.Post;
import com.example.postservice.model.PutRequest;
import com.example.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
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

    @Override
    public Post updatePosts(String postId, PutRequest putRequest) {
        Post post1 = postRepo.findByPostId(postId);
        post1.setPost(putRequest.getPost());
        post1.setPostId(putRequest.getPostId());
        post1.setPostedBy(putRequest.getPostedBy());
        post1.setUpdatedAt(LocalDateTime.now());
        postRepo.save(post1);
        return post1;
    }

    @Override
    public String deletePost(String postId) {
        Post post = postRepo.findByPostId(postId);
        postRepo.delete(post);
        return "Post deleted for id : " + postId;
    }
}
