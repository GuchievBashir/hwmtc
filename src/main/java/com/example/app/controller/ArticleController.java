package com.example.app.controller;

import com.example.app.domain.ArticleId;
import com.example.app.exceptions.*;
import com.example.app.request.article.ArticleCreateRequest;
import com.example.app.request.article.ArticleUpdateRequest;
import com.example.app.response.ErrorResponse;
import com.example.app.response.article.ArticleCreateResponse;
import com.example.app.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.HashSet;

public class ArticleController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

  private final Service service;
  private final ArticleService articleService;
  private final ObjectMapper objectMapper;

  public ArticleController(Service service, ArticleService articleService, ObjectMapper objectMapper) {
    this.service = service;
    this.articleService = articleService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    createArticle();
    getArticleById();
    updateArticle();
    deleteArticle();
    listArticles();
  }

  private void createArticle() {
    service.post("/api/articles", (Request request, Response response) -> {
      response.type("application/json");
      ArticleCreateRequest createRequest = objectMapper.readValue(request.body(), ArticleCreateRequest.class);
      try {
        var id = articleService.create(createRequest.getTitle(), new HashSet<>(createRequest.getTags()));
        response.status(201);
        return objectMapper.writeValueAsString(new ArticleCreateResponse(id.getValue()));
      } catch (ArticleCreateException e) {
        LOG.warn("Cannot create article", e);
        response.status(400);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
      }
    });
  }

  private void getArticleById() {
    service.get("/api/articles/:id", (Request request, Response response) -> {
      response.type("application/json");
      long id = Long.parseLong(request.params(":id"));
      try {
        var article = articleService.findById(new ArticleId(id));
        response.status(200);
        return objectMapper.writeValueAsString(article);
      } catch (ArticleFindException e) {
        LOG.warn("Cannot find article", e);
        response.status(404);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
      }
    });
  }

  private void updateArticle() {
    service.put("/api/articles/:id", (Request request, Response response) -> {
      response.type("application/json");
      long id = Long.parseLong(request.params(":id"));
      ArticleUpdateRequest updateRequest = objectMapper.readValue(request.body(), ArticleUpdateRequest.class);
      try {
        articleService.update(new ArticleId(id), updateRequest.getTitle());
        response.status(200);
        return "{}";
      } catch (ArticleUpdateException e) {
        LOG.warn("Cannot update article", e);
        response.status(404);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
      }
    });
  }

  private void deleteArticle() {
    service.delete("/api/articles/:id", (Request request, Response response) -> {
      response.type("application/json");
      long id = Long.parseLong(request.params(":id"));
      try {
        articleService.delete(new ArticleId(id));
        response.status(204);
        return "";
      } catch (ArticleDeleteException e) {
        LOG.warn("Cannot delete article", e);
        response.status(404);
        return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
      }
    });
  }

  private void listArticles() {
    service.get("/api/articles", (Request request, Response response) -> {
      response.type("application/json");
      var all = articleService.findAll();
      response.status(200);
      return objectMapper.writeValueAsString(all);
    });
  }
}
