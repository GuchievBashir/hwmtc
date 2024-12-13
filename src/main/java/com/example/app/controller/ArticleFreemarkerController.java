package com.example.app.controller;

import com.example.app.domain.Article;
import com.example.app.service.ArticleService;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Service;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArticleFreemarkerController implements Controller {

  private final Service service;
  private final ArticleService articleService;
  private final FreeMarkerEngine freeMarkerEngine;

  public ArticleFreemarkerController(
          Service service,
          ArticleService articleService,
          FreeMarkerEngine freeMarkerEngine
  ) {
    this.service = service;
    this.articleService = articleService;
    this.freeMarkerEngine = freeMarkerEngine;
  }

  @Override
  public void initializeEndpoints() {
    getAllArticlesHtml();
  }

  private void getAllArticlesHtml() {
    service.get(
            "/articles-html",
            (Request request, Response response) -> {
              response.type("text/html; charset=utf-8");
              List<Article> articles = articleService.findAll();

              List<Map<String, Object>> articleMapList = articles.stream()
                      .map(article -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", article.getId().getValue());
                        map.put("title", article.getTitle());
                        map.put("tags", article.getTags());
                        map.put("comments", article.getComments());
                        return map;
                      })
                      .collect(Collectors.toList());

              Map<String, Object> model = new HashMap<>();
              model.put("articles", articleMapList);

              return freeMarkerEngine.render(new ModelAndView(model, "index.ftl"));
            }
    );
  }
}
