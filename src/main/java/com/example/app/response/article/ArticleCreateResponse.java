package com.example.app.response.article;

public class ArticleCreateResponse {
  private final long id;

  public ArticleCreateResponse(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
