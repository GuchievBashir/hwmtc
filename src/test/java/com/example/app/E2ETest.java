package com.example.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import spark.Spark;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class E2ETest {

  private static final String BASE_URL = "http://localhost:4567";
  private final ObjectMapper objectMapper = new ObjectMapper();
  private HttpClient httpClient;

  @BeforeAll
  void startApp() {
    Main.main(new String[]{});
    httpClient = HttpClient.newHttpClient();
  }

  @AfterAll
  void stopApp() {
    Spark.stop();
  }

  @Test
  void fullFlowTest() throws IOException, InterruptedException {
    // 1. Создаем статью
    String createArticleJson = """
            {
              "title": "Test Article",
              "tags": ["test"]
            }
            """;
    HttpRequest createArticleRequest = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/api/articles"))
            .POST(HttpRequest.BodyPublishers.ofString(createArticleJson))
            .header("Content-Type", "application/json")
            .build();
    HttpResponse<String> createArticleResponse = httpClient.send(createArticleRequest, HttpResponse.BodyHandlers.ofString());
    assertEquals(201, createArticleResponse.statusCode());
    JsonNode createArticleBody = objectMapper.readTree(createArticleResponse.body());
    long articleId = createArticleBody.get("id").asLong();

    // 2. Добавляем комментарий
    String addCommentJson = """
            {
              "text": "Hello Comment",
              "articleId": %d
            }
            """.formatted(articleId);
    HttpRequest addCommentRequest = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/api/comments"))
            .POST(HttpRequest.BodyPublishers.ofString(addCommentJson))
            .header("Content-Type", "application/json")
            .build();
    HttpResponse<String> addCommentResponse = httpClient.send(addCommentRequest, HttpResponse.BodyHandlers.ofString());
    assertEquals(201, addCommentResponse.statusCode());
    JsonNode commentBody = objectMapper.readTree(addCommentResponse.body());
    long commentId = commentBody.get("id").asLong();

    // 3. Редактируем статью
    String editArticleJson = """
            {
              "title": "New Title"
            }
            """;
    HttpRequest editArticleRequest = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/api/articles/" + articleId))
            .PUT(HttpRequest.BodyPublishers.ofString(editArticleJson))
            .header("Content-Type", "application/json")
            .build();
    HttpResponse<String> editArticleResponse = httpClient.send(editArticleRequest, HttpResponse.BodyHandlers.ofString());
    assertEquals(200, editArticleResponse.statusCode());

    // 4. Удаляем комментарий
    HttpRequest deleteCommentRequest = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/api/comments/" + commentId))
            .DELETE()
            .build();
    HttpResponse<String> deleteCommentResponse = httpClient.send(deleteCommentRequest, HttpResponse.BodyHandlers.ofString());
    assertEquals(204, deleteCommentResponse.statusCode());

    // 5. Запрашиваем статью по ID
    HttpRequest getArticleRequest = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/api/articles/" + articleId))
            .GET()
            .build();
    HttpResponse<String> getArticleResponse = httpClient.send(getArticleRequest, HttpResponse.BodyHandlers.ofString());
    assertEquals(200, getArticleResponse.statusCode());

    JsonNode fetchedArticle = objectMapper.readTree(getArticleResponse.body());
    assertEquals("New Title", fetchedArticle.get("title").asText());
    assertTrue(fetchedArticle.get("comments").isArray());
    assertEquals(0, fetchedArticle.get("comments").size());
  }
}
