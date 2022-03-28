package com.example.postservice.controller;

import com.example.postservice.dto.PostDTO;
import com.example.postservice.model.Post;
import com.example.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(path="/api/v1/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping(path="")

    public ResponseEntity<PostDTO> createPost(@RequestBody Post post)
    {
        return  new ResponseEntity<PostDTO>(postService.createPost(post), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("postId") String postId) {

        return  new ResponseEntity<PostDTO>(postService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<PostDTO>> getPosts() {

        return  new ResponseEntity<List<PostDTO>>(postService.getPosts(), HttpStatus.OK);
    }

    @PutMapping(path = "/{postId}")
    public ResponseEntity<PostDTO> updatePosts(@PathVariable("postId") String postId,@RequestBody Post post){
        return new ResponseEntity<PostDTO>(postService.updatePosts(postId, post), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") String postId) {
        return new ResponseEntity<String>(postService.deletePost(postId), HttpStatus.OK);
    }

}
