package com.example.app.exceptions;

public class CommentIdDuplicatedException extends RuntimeException {
  public CommentIdDuplicatedException(String message) {
    super(message);
  }

  public CommentIdDuplicatedException(String message, Throwable cause) {
    super(message, cause);
  }
}
