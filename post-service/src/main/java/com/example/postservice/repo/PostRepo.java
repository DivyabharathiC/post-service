package com.example.postservice.repo;

import com.example.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post , String> {
}
