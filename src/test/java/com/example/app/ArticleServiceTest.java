package com.example.app;

import com.example.app.domain.Article;
import com.example.app.domain.ArticleId;
import com.example.app.exceptions.ArticleCreateException;
import com.example.app.exceptions.ArticleDeleteException;
import com.example.app.exceptions.ArticleFindException;
import com.example.app.exceptions.ArticleIdDuplicatedException;
import com.example.app.exceptions.ArticleNotFoundException;
import com.example.app.exceptions.ArticleUpdateException;
import com.example.app.repository.InMemoryArticleRepository;
import com.example.app.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceTest {

  private ArticleService articleService;
  private InMemoryArticleRepository articleRepository;

  @BeforeEach
  void setUp() {
    articleRepository = new InMemoryArticleRepository();
    articleService = new ArticleService(articleRepository);
  }

  @Test
  void shouldFindAllArticles() {
    ArticleId id1 = articleRepository.generateId();
    Article article1 = new Article(id1, "Title1", Set.of("tag1"), new ArrayList<>());
    articleRepository.create(article1);

    ArticleId id2 = articleRepository.generateId();
    Article article2 = new Article(id2, "Title2", Set.of("tag2"), new ArrayList<>());
    articleRepository.create(article2);

    List<Article> articles = articleService.findAll();

    assertEquals(2, articles.size());
    assertEquals("Title1", articles.get(0).getTitle());
    assertEquals("Title2", articles.get(1).getTitle());
  }

  @Test
  void shouldFindArticleById() {
    ArticleId id = articleRepository.generateId();
    Article article = new Article(id, "Title1", Set.of("tag1"), new ArrayList<>());
    articleRepository.create(article);

    Article foundArticle = articleService.findById(id);

    assertNotNull(foundArticle);
    assertEquals("Title1", foundArticle.getTitle());
  }

  @Test
  void shouldThrowArticleFindExceptionWhenArticleNotFound() {
    ArticleId id = new ArticleId(999);

    Exception exception = assertThrows(ArticleFindException.class, () -> {
      articleService.findById(id);
    });

    assertTrue(exception.getMessage().contains("Cannot find article by id=999"));
    assertTrue(exception.getCause() instanceof ArticleNotFoundException);
  }

  @Test
  void shouldCreateArticle() {
    String title = "New Article";
    Set<String> tags = Set.of("tag1");

    ArticleId createdId = articleService.create(title, tags);

    assertNotNull(createdId);
    Article createdArticle = articleRepository.findById(createdId);
    assertEquals(title, createdArticle.getTitle());
    assertEquals(tags, createdArticle.getTags());
    assertTrue(createdArticle.getComments().isEmpty());
  }

  @Test
  void shouldUpdateArticle() {
    ArticleId id = articleRepository.generateId();
    Article article = new Article(id, "Old Title", Set.of("tag1"), new ArrayList<>());
    articleRepository.create(article);

    String newTitle = "Updated Title";
    articleService.update(id, newTitle);

    Article updatedArticle = articleRepository.findById(id);
    assertEquals(newTitle, updatedArticle.getTitle());
  }

  @Test
  void shouldThrowArticleUpdateExceptionWhenArticleNotFound() {
    ArticleId id = new ArticleId(999);

    Exception exception = assertThrows(ArticleUpdateException.class, () -> {
      articleService.update(id, "New Title");
    });

    assertTrue(exception.getMessage().contains("Cannot find article with id=999"));
    assertTrue(exception.getCause() instanceof ArticleNotFoundException);
  }

}
