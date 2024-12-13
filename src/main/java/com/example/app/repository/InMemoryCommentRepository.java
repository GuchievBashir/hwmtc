package com.example.app.repository;

import com.example.app.domain.Comment;
import com.example.app.domain.CommentId;
import com.example.app.exceptions.CommentIdDuplicatedException;
import com.example.app.exceptions.CommentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCommentRepository implements CommentRepository {
  private final AtomicLong nextId = new AtomicLong(0);
  private final Map<CommentId, Comment> commentsMap = new ConcurrentHashMap<>();

  @Override
  public CommentId generateId() {
    return new CommentId(nextId.incrementAndGet());
  }

  @Override
  public List<Comment> findAll() {
    return new ArrayList<>(commentsMap.values());
  }

  @Override
  public Comment findById(CommentId commentId) {
    Comment c = commentsMap.get(commentId);
    if (c == null) {
      throw new CommentNotFoundException("Cannot find comment by id=" + commentId.getValue());
    }
    return c;
  }

  @Override
  public synchronized void create(Comment comment) {
    if (commentsMap.get(comment.getId()) != null) {
      throw new CommentIdDuplicatedException("Comment with given id already exists: " + comment.getId().getValue());
    }
    commentsMap.put(comment.getId(), comment);
  }

  @Override
  public synchronized void update(Comment comment) {
    if (commentsMap.get(comment.getId()) == null) {
      throw new CommentNotFoundException("Cannot find comment by id=" + comment.getId().getValue());
    }
    commentsMap.put(comment.getId(), comment);
  }

  @Override
  public void delete(CommentId commentId) {
    if (commentsMap.remove(commentId) == null) {
      throw new CommentNotFoundException("Cannot find comment by id=" + commentId.getValue());
    }
  }
}
