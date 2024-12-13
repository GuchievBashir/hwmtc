package com.example.app.request.comment;

public class CommentCreateRequest {
  private String text;
  private long articleId;

  public String getText() {
    return text;
  }

  public long getArticleId() {
    return articleId;
  }
}
