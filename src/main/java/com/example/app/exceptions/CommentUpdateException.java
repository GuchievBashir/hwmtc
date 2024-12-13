package com.example.app.exceptions;

public class CommentUpdateException extends RuntimeException {
  public CommentUpdateException(String message) {
    super(message);
  }
  public CommentUpdateException(String message, Throwable cause) {
    super(message, cause);
  }
}
