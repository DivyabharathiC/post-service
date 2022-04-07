package com.example.postservice.repo;

import com.example.postservice.dto.PostDTO;
import com.example.postservice.enums.Gender;
import com.example.postservice.model.Post;
import com.example.postservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
class PostRepoTest {

    @Autowired
    PostRepo postRepo;

    @BeforeEach
    void initUseCase() {
        Post post = createPost();
        postRepo.save(post);
    }

    @AfterEach
    public void destroyByAll() {
        postRepo.deleteAll();
    }


    @Test
    void findByPostId() {
        Post post = postRepo.findByPostId(createPost().getPostId());
        assertEquals("123", post.getPostId());
    }

    private Post createPost() {
        Post post = new Post();

        post.setPost("post1");
        post.setPostedBy("postedbyrepo");
        post.setPostId("123");

        return post;
    }

}