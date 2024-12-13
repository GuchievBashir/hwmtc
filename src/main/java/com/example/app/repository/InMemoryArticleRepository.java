package com.example.app.repository;

import com.example.app.domain.Article;
import com.example.app.domain.ArticleId;
import com.example.app.exceptions.ArticleIdDuplicatedException;
import com.example.app.exceptions.ArticleNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryArticleRepository implements ArticleRepository {
  private final AtomicLong nextId = new AtomicLong(0);
  private final Map<ArticleId, Article> articlesMap = new ConcurrentHashMap<>();

  @Override
  public ArticleId generateId() {
    return new ArticleId(nextId.incrementAndGet());
  }

  @Override
  public List<Article> findAll() {
    return new ArrayList<>(articlesMap.values());
  }

  @Override
  public Article findById(ArticleId articleId) {
    Article article = articlesMap.get(articleId);
    if (article == null) {
      throw new ArticleNotFoundException("Cannot find article by id=" + articleId.getValue());
    }
    return article;
  }

  @Override
  public synchronized void create(Article article) {
    if (articlesMap.get(article.getId()) != null) {
      throw new ArticleIdDuplicatedException("Article with the given id already exists: " + article.getId().getValue());
    }
    articlesMap.put(article.getId(), article);
  }

  @Override
  public synchronized void update(Article article) {
    if (articlesMap.get(article.getId()) == null) {
      throw new ArticleNotFoundException("Cannot find article by id=" + article.getId().getValue());
    }
    articlesMap.put(article.getId(), article);
  }

  @Override
  public void delete(ArticleId articleId) {
    if (articlesMap.remove(articleId) == null) {
      throw new ArticleNotFoundException("Cannot find article by id=" + articleId.getValue());
    }
  }
}
