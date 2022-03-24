package com.example.postservice.Feign;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name="comment-service")
public interface FeignComment {

    @LoadBalanced
    @GetMapping("/api/v1/posts/{postId}/comments/count")
     int getCommentCount(@PathVariable("postId") String postId);





}