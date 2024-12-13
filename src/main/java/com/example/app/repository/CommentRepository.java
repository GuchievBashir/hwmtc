package com.example.app.repository;

import com.example.app.domain.Comment;
import com.example.app.domain.CommentId;
import com.example.app.exceptions.CommentIdDuplicatedException;
import com.example.app.exceptions.CommentNotFoundException;

import java.util.List;

public interface CommentRepository {
  CommentId generateId();
  List<Comment> findAll();
  Comment findById(CommentId commentId) throws CommentNotFoundException;
  void create(Comment comment) throws CommentIdDuplicatedException;
  void update(Comment comment) throws CommentNotFoundException;
  void delete(CommentId commentId) throws CommentNotFoundException;
}
