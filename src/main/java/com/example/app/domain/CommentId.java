package com.example.app.domain;

public final class CommentId {
  private final long value;

  public CommentId(long value) {
    this.value = value;
  }

  public long getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CommentId)) return false;
    CommentId commentId = (CommentId) o;
    return value == commentId.value;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(value);
  }
}
