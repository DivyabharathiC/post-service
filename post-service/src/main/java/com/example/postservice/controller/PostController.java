package com.example.postservice.controller;

import com.example.postservice.model.Post;
import com.example.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping(path="/post-new")

    public ResponseEntity<Post> createPost(@RequestBody Post post)
    {
        return  new ResponseEntity<Post>(postService.createPost(post), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") String postId) {

        return  new ResponseEntity<Post>(postService.getPost(postId), HttpStatus.OK);
    }

}
