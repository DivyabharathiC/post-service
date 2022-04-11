package com.example.postservice.service;

import com.example.postservice.Feign.CommentFeignClient;
import com.example.postservice.Feign.LikeFeignClient;
import com.example.postservice.Feign.UserFeignClient;
import com.example.postservice.dto.PostDTO;
import com.example.postservice.enums.Gender;
import com.example.postservice.model.Post;
import com.example.postservice.model.User;
import com.example.postservice.repo.PostRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostServiceImplTest {

    @InjectMocks
    PostServiceImpl postService;
    @Mock
    PostRepo postRepo;
    @Mock
    UserFeignClient userFeignClient;
    @Mock
    CommentFeignClient commentFeignClient;
    @Mock
    LikeFeignClient likeFeignClient;


    @Test
    @DisplayName("validate get post details by post ID")
    public void getPost() {
        Post post = updatePostRequest();
        Mockito.when(postRepo.findByPostId(post.getPostId())).thenReturn(post);
        assertEquals(post, postRepo.findByPostId(post.getPostId()));
    }

    @Test
    @DisplayName("validate get all posts ")
    public void getPosts() {
        Post post = createPostRequest();
        List<Post> postLists = getListOfPosts();
        Mockito.when(postRepo.findAll()).thenReturn(postLists);
        assertEquals(postLists, postRepo.findAll());
    }

    @Test
    @DisplayName("validate delete post details by post ID")
    public void deletePost() {
        Post postDetails = createOnePost();
        postRepo.delete(postDetails);
        verify(postRepo, times(1)).delete(postDetails);
    }


    @Test
    @DisplayName("validate create post")
    public void createPost() {
        Post postRequest = createPostRequest();

        Post post = new Post();
        post.setPost(postRequest.getPost());
        post.setPostId(postRequest.getPostId());
        post.setPostedBy(postRequest.getPostedBy());

        Mockito.when(postRepo.save(post)).thenReturn(post);
        assertThat(postRepo.findById(post.getPostId())).isNotNull();
    }

    private Post createPostRequest() {
        Post post = new Post();

        post.setPost("Hello test user.. How are you?");
        post.setPostedBy("77");
        post.setPostId("1235");
        return post;
    }

    private Post updatePostRequest() {
        Post post = new Post();
        User response = createOneUserDataToPost();
        post.setPost("Hello test user.. How are you? This is updated one");
        post.setPostedBy(String.valueOf(response));
        post.setPostId("123");
        return post;
    }


    private Post createOnePost() {
        Post post1 = new Post();
        post1.setPostId("1");
        post1.setPost("Hi");

        return post1;
    }


    private PostDTO createPostResponse() {
        PostDTO post = new PostDTO();
        User response = createOneUserDataToPost();
        post.setPost("Hello Service implementation test user.. How are you?");
        post.setPostedBy(response);
        post.setPostId("456");
        post.setCommentCounts(1);
        post.setLikeCounts(1);
        return post;
    }

    private User createOneUserDataToPost() {
        User userDetails = new User();
        userDetails.setUserId("53");
        userDetails.setFirstName("Thamarai");
        userDetails.setMiddleName("");
        userDetails.setLastName("M");
        userDetails.setPhoneNumber("9578583448");
        userDetails.setDateOfBirth(new Date(2021, 8, 01));
        userDetails.setGender(Gender.MALE);
        userDetails.setEmployeeId("5646");
        userDetails.setBloodGroup("AB+");
        userDetails.setEmail("kannan@gmail.com");
        userDetails.setAddress("Chennai,TS");
        return userDetails;
    }

    private List<Post> getListOfPosts() {
        List<Post> lists = new ArrayList<>();
        Post post = new Post();
        User response = createOneUserDataToPost();
        post.setPost("Hello test user.. How are you?");
        post.setPostedBy("53");
        post.setPostId("123");

        lists.add(post);

        Post post2 = new Post();
        post2.setPost("Hello second test user.. How are you?");
        post2.setPostedBy("53");
        post2.setPostId("123");

        lists.add(post2);
        return lists;
    }

        @Test
        @DisplayName("validate get post details by post ID")
        public void updatePosts() {
            Post post = updatePostRequest();
            Mockito.when(postRepo.findByPostId(post.getPostId())).thenReturn(post);
            assertEquals(post, postRepo.findByPostId(post.getPostId()));
        }


}