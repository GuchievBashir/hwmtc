package com.example.app;

import com.example.app.controller.ArticleController;
import com.example.app.controller.ArticleFreemarkerController;
import com.example.app.controller.CommentController;
import com.example.app.repository.InMemoryArticleRepository;
import com.example.app.repository.InMemoryCommentRepository;
import com.example.app.service.ArticleService;
import com.example.app.service.CommentService;
import com.example.app.template.TemplateFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Service;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    Service service = Service.ignite().port(4567);
    ObjectMapper objectMapper = new ObjectMapper();

    ArticleService articleService = new ArticleService(new InMemoryArticleRepository());
    CommentService commentService = new CommentService(new InMemoryCommentRepository());

    Application application = new Application(
            List.of(
                    new ArticleFreemarkerController(
                            service,
                            articleService,
                            TemplateFactory.freeMarkerEngine()
                    ),
                    new ArticleController(
                            service,
                            articleService,
                            objectMapper
                    ),
                    new CommentController(
                            service,
                            commentService,
                            objectMapper
                    )
            )
    );
    application.start();

    service.awaitInitialization();
  }
}
