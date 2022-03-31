package com.example.postservice.exception;


public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String s) {
       super(s);
    }
}
