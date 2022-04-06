package com.example.postservice.controller;


import com.example.postservice.dto.PostDTO;
import com.example.postservice.enums.Gender;
import com.example.postservice.model.Post;
import com.example.postservice.model.User;
import com.example.postservice.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
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


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    void createPost() throws Exception {
        PostDTO response = createPostResponse();
        Post post = createNewPostRequest();
        Mockito.when(postService.createPost(post)).thenReturn(response);
        mockMvc.perform(post("/api/v1/posts/")
                        .content(asJsonString(post))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private PostDTO createPostResponse() {
        PostDTO post = new PostDTO();
        User response = createOneUserDataToPost();
        post.setPost("post1");
        post.setPostedBy(response);
        post.setPostId("100");
        return post;
    }
    private Post createNewPostRequest() {
        Post post = new Post();
        post.setPost("post1");
        post.setPostedBy("10");
        return post;
    }
    private User createOneUserDataToPost() {
        User userDetails = new User();
        userDetails.setUserId("10");
        userDetails.setFirstName("Divya");
        userDetails.setMiddleName("bharathi");
        userDetails.setLastName("C");
        userDetails.setPhoneNumber("9123874562");
        userDetails.setDateOfBirth(new Date(1998, 7, 24));
        userDetails.setGender(Gender.FEMALE);
        userDetails.setEmployeeId("6802");
        userDetails.setBloodGroup("A+");
        userDetails.setEmail("divya@gmail.com");
        userDetails.setPassword("divi123");
        userDetails.setAddress("Sirkali");
        return userDetails;
    }

    @Test
    @DisplayName("Get all posts")
    public void getAllPosts() throws Exception {
        Post response = createNewPostRequest();
        List<PostDTO> postLists = getListOfPosts();
        PostDTO postDetails = createPostResponse();
        Mockito.when(postService.getPosts(1,2)).thenReturn(postLists);
        mockMvc.perform(get("/api/v1/posts/")
                        .content(asJsonString(response))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    private List<PostDTO> getListOfPosts() {
        List<PostDTO> lists = new ArrayList<>();
        PostDTO post = new PostDTO();
        User response = createOneUserDataToPost();
        post.setPost("post1");
        post.setPostedBy(response);
        post.setPostId("123");
        lists.add(post);

        PostDTO post2 = new PostDTO();
        post2.setPost("post1");
        post2.setPostedBy(response);
        lists.add(post2);

        return lists;
    }
    @Test
    @DisplayName("Retrieve the Post details by Id")
    public void getPostByPostId() throws Exception {
        Post response = createNewPostRequest();
        PostDTO postDetails = createPostResponse();
        Mockito.when(postService.getPost(response.getPostId())).thenReturn(postDetails);
        mockMvc.perform(get("/api/v1/posts/123")
                        .content(asJsonString(response))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }




}