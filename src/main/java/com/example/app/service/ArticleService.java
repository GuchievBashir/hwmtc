package com.example.app.service;

import com.example.app.domain.Article;
import com.example.app.domain.ArticleId;
import com.example.app.exceptions.*;
import com.example.app.repository.ArticleRepository;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class ArticleService {
  private final ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  public Article findById(ArticleId articleId) {
    try {
      return articleRepository.findById(articleId);
    } catch (ArticleNotFoundException e) {
      throw new ArticleFindException("Cannot find article by id=" + articleId.getValue(), e);
    }
  }

  public ArticleId create(String title, Set<String> tags) {
    ArticleId articleId = articleRepository.generateId();
    Article article = new Article(articleId, title, tags, new ArrayList<>());
    try {
      articleRepository.create(article);
      return articleId;
    } catch (ArticleIdDuplicatedException e) {
      throw new ArticleCreateException("Cannot create article with id=" + articleId.getValue(), e);
    }
  }

  public void update(ArticleId articleId, String newTitle) {
    Article article;
    try {
      article = articleRepository.findById(articleId);
    } catch (ArticleNotFoundException e) {
      throw new ArticleUpdateException("Cannot find article with id=" + articleId.getValue(), e);
    }

    Article updatedArticle = article.withTitle(newTitle);
    try {
      articleRepository.update(updatedArticle);
    } catch (ArticleNotFoundException e) {
      throw new ArticleUpdateException("Cannot update article with id=" + articleId.getValue(), e);
    }
  }

  public void delete(ArticleId articleId) {
    try {
      articleRepository.delete(articleId);
    } catch (ArticleNotFoundException e) {
      throw new ArticleDeleteException("Cannot delete article with id=" + articleId.getValue(), e);
    }
  }
}
