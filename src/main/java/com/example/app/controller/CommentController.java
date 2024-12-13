package com.example.app.controller;

import com.example.app.domain.ArticleId;
import com.example.app.domain.CommentId;
import com.example.app.exceptions.*;
import com.example.app.request.comment.CommentCreateRequest;
import com.example.app.request.comment.CommentUpdateRequest;
import com.example.app.response.ErrorResponse;
import com.example.app.response.comment.CommentCreateResponse;
import com.example.app.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

public class CommentController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

  private final Service service;
  private final CommentService commentService;
  private final ObjectMapper objectMapper;

  public CommentController(Service service, CommentService commentService, ObjectMapper objectMapper) {
    this.service = service;
    this.commentService = commentService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    createComment();
    getCommentById();
    updateComment();
    deleteComment();
    listComments();
  }

  private void createComment() {
    service.post("/api/comments", (Request request, Response response) -> {
      response.type("application/json");
      CommentCreateRequest createRequest = objectMapper.readValue(request.body(), CommentCreateRequest.class);
      try {
        var id = commentService.create(new ArticleId(createRequest.getArticleId()), createRequest.getText());
        response.status(201);
        return objectMapper.writeValueAsString(new CommentCreateResponse(id.getValue()));
      } catch (CommentCreateException e) {
        LOG.warn("Cannot create comment", e);
        response.status(400);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
      }
    });
  }

  private void getCommentById() {
    service.get("/api/comments/:id", (Request request, Response response) -> {
      response.type("application/json");
      long id = Long.parseLong(request.params(":id"));
      try {
        var comment = commentService.findById(new CommentId(id));
        response.status(200);
        return objectMapper.writeValueAsString(comment);
      } catch (CommentFindException e) {
        LOG.warn("Cannot find comment", e);
        response.status(404);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
      }
    });
  }

  private void updateComment() {
    service.put("/api/comments/:id", (Request request, Response response) -> {
      response.type("application/json");
      long id = Long.parseLong(request.params(":id"));
      CommentUpdateRequest updateRequest = objectMapper.readValue(request.body(), CommentUpdateRequest.class);
      try {
        commentService.update(new CommentId(id), updateRequest.getText());
        response.status(200);
        return "{}";
      } catch (CommentUpdateException e) {
        LOG.warn("Cannot update comment", e);
        response.status(404);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
      }
    });
  }

  private void deleteComment() {
    service.delete("/api/comments/:id", (Request request, Response response) -> {
      response.type("application/json");
      long id = Long.parseLong(request.params(":id"));
      try {
        commentService.delete(new CommentId(id));
        response.status(204);
        return "";
      } catch (CommentDeleteException e) {
        LOG.warn("Cannot delete comment", e);
        response.status(404);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
      }
    });
  }

  private void listComments() {
    service.get("/api/comments", (Request request, Response response) -> {
      response.type("application/json");
      var all = commentService.findAll();
      response.status(200);
      return objectMapper.writeValueAsString(all);
    });
  }
}
