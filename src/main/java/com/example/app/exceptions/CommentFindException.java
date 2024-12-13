package com.example.app.exceptions;

public class CommentFindException extends RuntimeException {
  public CommentFindException(String message) {
    super(message);
  }
  public CommentFindException(String message, Throwable cause) {
    super(message, cause);
  }
}
