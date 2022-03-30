package com.example.postservice.controller;

import com.example.postservice.dto.PostDTO;
import com.example.postservice.exception.PostNotFoundException;
import com.example.postservice.model.Post;
import com.example.postservice.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.postservice.constant.Constant.PostNotFound;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(path="/api/v1/posts")
public class PostController {

    private static Logger logger = LoggerFactory.getLogger(PostController.class);
    @Autowired
    PostService postService;

    @PostMapping(path="")

    public ResponseEntity<PostDTO> createPost(@RequestBody Post post)
    {
        logger.info("Starting of createPost request from post application");
        return  new ResponseEntity<PostDTO>(postService.createPost(post), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("postId") String postId) {
        logger.info("Starting of getPostbyid request from post application");
        return new ResponseEntity<PostDTO>(postService.getPost(postId), HttpStatus.OK);

    }

    @GetMapping(path = "")
    public ResponseEntity<List<PostDTO>> getPosts() {

        logger.info("Starting of getPosts request from post application");

        return new ResponseEntity<List<PostDTO>>(postService.getPosts(), HttpStatus.OK);
    }

    @PutMapping(path = "/{postId}")
    public ResponseEntity<PostDTO> updatePosts(@PathVariable("postId") String postId,@RequestBody Post post){
        logger.info("Starting of updatePosts request from post application");
        return new ResponseEntity<PostDTO>(postService.updatePosts(postId, post), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") String postId) {
        logger.info("Starting of deletePost request from post application");
        return new ResponseEntity<String>(postService.deletePost(postId), HttpStatus.OK);
    }

}
