package com.example.app;

import com.example.app.domain.ArticleId;
import com.example.app.domain.Comment;
import com.example.app.domain.CommentId;
import com.example.app.exceptions.CommentCreateException;
import com.example.app.exceptions.CommentDeleteException;
import com.example.app.exceptions.CommentFindException;
import com.example.app.exceptions.CommentIdDuplicatedException;
import com.example.app.exceptions.CommentNotFoundException;
import com.example.app.exceptions.CommentUpdateException;
import com.example.app.repository.InMemoryCommentRepository;
import com.example.app.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {

  private CommentService commentService;
  private InMemoryCommentRepository commentRepository;

  @BeforeEach
  void setUp() {
    commentRepository = new InMemoryCommentRepository();
    commentService = new CommentService(commentRepository);
  }

  @Test
  void shouldFindAllComments() {
    CommentId id1 = commentRepository.generateId();
    Comment comment1 = new Comment(id1, new ArticleId(1), "Comment1");
    commentRepository.create(comment1);

    CommentId id2 = commentRepository.generateId();
    Comment comment2 = new Comment(id2, new ArticleId(1), "Comment2");
    commentRepository.create(comment2);

    List<Comment> comments = commentService.findAll();

    assertEquals(2, comments.size());
    assertEquals("Comment1", comments.get(0).getText());
    assertEquals("Comment2", comments.get(1).getText());
  }

  @Test
  void shouldFindCommentById() {
    CommentId id = commentRepository.generateId();
    Comment comment = new Comment(id, new ArticleId(1), "Comment1");
    commentRepository.create(comment);

    Comment foundComment = commentService.findById(id);

    assertNotNull(foundComment);
    assertEquals("Comment1", foundComment.getText());
  }

  @Test
  void shouldThrowCommentFindExceptionWhenCommentNotFound() {
    CommentId id = new CommentId(999);

    Exception exception = assertThrows(CommentFindException.class, () -> {
      commentService.findById(id);
    });

    assertTrue(exception.getMessage().contains("Cannot find comment by id=999"));
    assertTrue(exception.getCause() instanceof CommentNotFoundException);
  }

  @Test
  void shouldCreateComment() {
    ArticleId articleId = new ArticleId(1);
    String text = "New Comment";

    CommentId createdId = commentService.create(articleId, text);

    assertNotNull(createdId);
    Comment createdComment = commentRepository.findById(createdId);
    assertEquals(text, createdComment.getText());
    assertEquals(articleId, createdComment.getArticleId());
  }

  @Test
  void shouldUpdateComment() {
    CommentId id = commentRepository.generateId();
    Comment comment = new Comment(id, new ArticleId(1), "Old Comment");
    commentRepository.create(comment);

    String newText = "Updated Comment";
    commentService.update(id, newText);

    Comment updatedComment = commentRepository.findById(id);
    assertEquals(newText, updatedComment.getText());
  }

  @Test
  void shouldThrowCommentUpdateExceptionWhenCommentNotFound() {
    CommentId id = new CommentId(999);

    Exception exception = assertThrows(CommentUpdateException.class, () -> {
      commentService.update(id, "Updated Comment");
    });

    assertTrue(exception.getMessage().contains("Cannot find comment with id=999"));
    assertTrue(exception.getCause() instanceof CommentNotFoundException);
  }

  @Test
  void shouldThrowCommentDeleteExceptionWhenCommentNotFound() {
    CommentId id = new CommentId(999);

    Exception exception = assertThrows(CommentDeleteException.class, () -> {
      commentService.delete(id);
    });

    assertTrue(exception.getMessage().contains("Cannot delete comment with id=999"));
    assertTrue(exception.getCause() instanceof CommentNotFoundException);
  }
}
