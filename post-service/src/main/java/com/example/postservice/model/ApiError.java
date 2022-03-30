package com.example.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiError {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private List<String> errors;
    private String path;
}
