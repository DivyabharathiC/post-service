package com.example.postservice.service;

import com.example.postservice.Feign.CommentFeignClient;
import com.example.postservice.Feign.LikeFeignClient;
import com.example.postservice.Feign.UserFeignClient;
import com.example.postservice.dto.PostDTO;
import com.example.postservice.exception.PostNotFoundException;
import com.example.postservice.model.Post;
import com.example.postservice.model.User;
import com.example.postservice.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.postservice.constant.Constant.PostNotFound;

@Service
public class PostServiceImpl implements  PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    private CommentFeignClient commentFeignClient;
    @Autowired
    private LikeFeignClient likeFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;


    @Override
    public PostDTO createPost(Post post) {

        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(post.getCreatedAt());
        postRepo.save(post);

        PostDTO postDTO = new PostDTO(post.getPostId(), post.getPost(),
                userFeignClient.getUser(post.getPostedBy()),
                post.getCreatedAt(), post.getUpdatedAt(), 0, 0);

        return postDTO;
    }

    @Override
    public PostDTO getPost(String postId) {
        try {
            Post post = postRepo.findById(postId).get();

            PostDTO postDTO = new PostDTO(post.getPostId(), post.getPost(),
                    userFeignClient.getUser(post.getPostedBy()), post.getCreatedAt(), post.getUpdatedAt(),
                    commentFeignClient.getCommentCount(post.getPostId()),
                    likeFeignClient.getCount(post.getPostId()));

            return postDTO;
        } catch (Exception e) {
            throw new PostNotFoundException(PostNotFound);
        }
    }

    @Override
    public List<PostDTO> getPosts() {

            List<Post> posts = postRepo.findAll();
            if (posts.isEmpty()) {
                throw new PostNotFoundException( PostNotFound);
            }

                List<PostDTO> postDTOS = new ArrayList<>();

                for (Post post : posts) {
                    User user = userFeignClient.getUser(post.getPostedBy());
                    Integer commentCount = commentFeignClient.getCommentCount(post.getPostId());
                    Integer likeCount = likeFeignClient.getCount(post.getPostId());
                    postDTOS.add(new PostDTO(post.getPostId(), post.getPost(),
                            user, post.getCreatedAt(), post.getUpdatedAt(),
                            commentCount, likeCount));
                }
                return postDTOS;
            }







    @Override
    public PostDTO updatePosts(String postId, Post post) {
        post.setPostId(postId);
        post.setCreatedAt(postRepo.findById(postId).get().getCreatedAt());
        post.setUpdatedAt(LocalDateTime.now());
        postRepo.save(post);

        PostDTO postDTO= new PostDTO(post.getPostId(),post.getPost(),
                userFeignClient.getUser(post.getPostedBy()),post.getCreatedAt(),post.getUpdatedAt(),
                likeFeignClient.getCount(post.getPostId()),
                commentFeignClient.getCommentCount(post.getPostId()));

        return postDTO;
    }

    @Override
    public String deletePost(String postId) {
        Post post = postRepo.findByPostId(postId);
        postRepo.delete(post);
        return "Post deleted for id : " + postId;
    }
}
