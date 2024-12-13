package com.example.app.repository;

import com.example.app.domain.Article;
import com.example.app.domain.ArticleId;
import com.example.app.exceptions.ArticleIdDuplicatedException;
import com.example.app.exceptions.ArticleNotFoundException;

import java.util.List;

public interface ArticleRepository {
  ArticleId generateId();
  List<Article> findAll();
  Article findById(ArticleId articleId) throws ArticleNotFoundException;
  void create(Article article) throws ArticleIdDuplicatedException;
  void update(Article article) throws ArticleNotFoundException;
  void delete(ArticleId articleId) throws ArticleNotFoundException;
}
