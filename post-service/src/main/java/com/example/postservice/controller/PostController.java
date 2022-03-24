package com.example.postservice.controller;

import com.example.postservice.model.Post;
import com.example.postservice.model.PutRequest;
import com.example.postservice.model.Response;
import com.example.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping(path="")

    public ResponseEntity<Post> createPost(@RequestBody Post post)
    {
        return  new ResponseEntity<Post>(postService.createPost(post), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") String postId) {

        return  new ResponseEntity<Post>(postService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Response>> getPosts() {

        return  new ResponseEntity<List<Response>>(postService.getPosts(), HttpStatus.OK);
    }

    @PutMapping(path = "/{postId}")
    public ResponseEntity<Post> updatePosts(@PathVariable("postId") String postId,@RequestBody PutRequest putRequest){
        return new ResponseEntity<Post>(postService.updatePosts(postId, putRequest), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") String postId) {
        return new ResponseEntity<String>(postService.deletePost(postId), HttpStatus.OK);
    }

}
