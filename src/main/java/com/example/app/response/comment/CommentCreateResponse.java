package com.example.app.response.comment;

public class CommentCreateResponse {
  private final long id;

  public CommentCreateResponse(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
