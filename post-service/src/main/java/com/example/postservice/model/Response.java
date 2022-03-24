package com.example.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {


    private String postId;
    private String post;
    private User postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer commentCounts;
    private Integer likeCounts;

}
