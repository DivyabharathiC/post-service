package com.example.postservice.Feign;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="like-service")
public interface FeignLike {



    @LoadBalanced
        @GetMapping("/postsOrComments/{postOrCommentId}/likes/count")
         Integer getCount( @PathVariable("postOrCommentId") String postOrCommentId);




}
