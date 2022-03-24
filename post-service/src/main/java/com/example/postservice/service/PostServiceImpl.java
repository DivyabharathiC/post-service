package com.example.postservice.service;

import com.example.postservice.Feign.FeignComment;
import com.example.postservice.Feign.FeignLike;
import com.example.postservice.Feign.FeignUser;
import com.example.postservice.model.Post;
import com.example.postservice.model.PutRequest;
import com.example.postservice.model.Response;
import com.example.postservice.model.User;
import com.example.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements  PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    private FeignComment feignComment;
    @Autowired
    private FeignLike feignLike;
    @Autowired
    private FeignUser feignUser;


    @Override
    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(post.getCreatedAt());
        return postRepo.save(post) ;
    }

    @Override
    public Post getPost(String postId) {
        return postRepo.findById(postId).get();
    }

    @Override
    public List<Response> getPosts() {
        List<Post> posts= postRepo.findAll();
        List<Response> responses=new ArrayList<>();

        for(Post post:posts){
            User user=feignUser.getUser(post.getPostedBy());
            Integer commentCount=feignComment.getCommentCount(post.getPostId());
            Integer likeCount=feignLike.getCount(post.getPostId());
            System.out.println(commentCount+" "+likeCount);
            responses.add(new Response(post.getPostId(),post.getPost(),
                    user,post.getCreatedAt(),post.getUpdatedAt(),
                    commentCount,likeCount));
    }
        return  responses;
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
