package com.example.app.domain;

public final class ArticleId {
  private final long value;

  public ArticleId(long value) {
    this.value = value;
  }

  public long getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ArticleId)) return false;
    ArticleId articleId = (ArticleId) o;
    return value == articleId.value;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(value);
  }
}
