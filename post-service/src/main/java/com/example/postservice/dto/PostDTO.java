package com.example.postservice.dto;

import com.example.postservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    @Id
    private String postId;
    private String post;
    private User postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer commentCounts;
    private Integer likeCounts;
}
