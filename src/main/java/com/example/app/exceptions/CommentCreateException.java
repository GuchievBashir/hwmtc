package com.example.app.exceptions;

public class CommentCreateException extends RuntimeException {
  public CommentCreateException(String message) {
    super(message);
  }
  public CommentCreateException(String message, Throwable cause) {
    super(message, cause);
  }
}
