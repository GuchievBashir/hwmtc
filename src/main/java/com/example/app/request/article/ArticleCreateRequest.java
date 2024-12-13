package com.example.app.request.article;

import java.util.List;

public class ArticleCreateRequest {
  private String title;
  private List<String> tags;

  public String getTitle() {
    return title;
  }

  public List<String> getTags() {
    return tags;
  }
}
