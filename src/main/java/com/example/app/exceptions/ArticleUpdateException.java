package com.example.app.exceptions;

public class ArticleUpdateException extends RuntimeException {
  public ArticleUpdateException(String message) {
    super(message);
  }
  public ArticleUpdateException(String message, Throwable cause) {
    super(message, cause);
  }
}
