package com.example.postservice.controller;


import com.example.postservice.model.Post;
import com.example.postservice.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(PostController.class)
class PostControllerTest {


    @MockBean
    PostService postService;

    @Autowired
    MockMvc mockMvc;

    public static String asJsonString(final Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    void createPost() throws Exception {
//        Post post=createNewPost();
//        Mockito.when(postService.createPost(post)).thenReturn(post);
//        mockMvc.perform(post("/posts")
//                .content(asJsonString(post))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }
//
//    private Post createNewPost() {
//        Post post=new Post();
//        post.setPostId("1");
//        post.setPost("newpost");
//        post.setPostedBy("divya");
//
//        return post;
//    }
}