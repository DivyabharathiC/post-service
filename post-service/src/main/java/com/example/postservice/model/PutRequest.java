package com.example.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PutRequest {
    private String postId;
    private String post;
    private String postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
