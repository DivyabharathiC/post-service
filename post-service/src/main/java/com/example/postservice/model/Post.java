package com.example.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Post")

public class Post {

    @Id
    private String postId;
    @NotEmpty(message = "Post is required")
    private String post;
    @NotEmpty(message = "PostedBy is required")
    private String postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
