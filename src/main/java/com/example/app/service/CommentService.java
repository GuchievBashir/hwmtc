package com.example.app.service;

import com.example.app.domain.Comment;
import com.example.app.domain.CommentId;
import com.example.app.domain.ArticleId;
import com.example.app.exceptions.*;
import com.example.app.repository.CommentRepository;

import java.util.List;

public class CommentService {
  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public List<Comment> findAll() {
    return commentRepository.findAll();
  }

  public Comment findById(CommentId commentId) {
    try {
      return commentRepository.findById(commentId);
    } catch (CommentNotFoundException e) {
      throw new CommentFindException("Cannot find comment by id=" + commentId.getValue(), e);
    }
  }

  public CommentId create(ArticleId articleId, String text) {
    CommentId commentId = commentRepository.generateId();
    Comment comment = new Comment(commentId, articleId, text);
    try {
      commentRepository.create(comment);
      return commentId;
    } catch (CommentIdDuplicatedException e) {
      throw new CommentCreateException("Cannot create comment with id=" + commentId.getValue(), e);
    }
  }

  public void update(CommentId commentId, String newText) {
    Comment c;
    try {
      c = commentRepository.findById(commentId);
    } catch (CommentNotFoundException e) {
      throw new CommentUpdateException("Cannot find comment with id=" + commentId.getValue(), e);
    }

    Comment updated = c.withText(newText);
    try {
      commentRepository.update(updated);
    } catch (CommentNotFoundException e) {
      throw new CommentUpdateException("Cannot update comment with id=" + commentId.getValue(), e);
    }
  }

  public void delete(CommentId commentId) {
    try {
      commentRepository.delete(commentId);
    } catch (CommentNotFoundException e) {
      throw new CommentDeleteException("Cannot delete comment with id=" + commentId.getValue(), e);
    }
  }
}
